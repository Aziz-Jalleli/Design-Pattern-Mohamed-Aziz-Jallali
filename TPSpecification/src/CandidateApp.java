import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class CandidateApp extends JFrame {

    // --- Data ---
    private List<Candidate> candidates = new ArrayList<>();

    // --- Input fields ---
    private JTextField nameField, ageField, gradeField, expField;
    private JCheckBox scholarshipField;

    // --- Criteria checkboxes ---
    private JCheckBox cbAge, cbGrade, cbExperience, cbScholarship;

    // --- Tables ---
    private DefaultTableModel allModel, filteredModel;
    private JTable allTable, filteredTable;

    // --- Status ---
    private JLabel statusLabel;

    public CandidateApp() {
        super("Gestion des Candidats — Specification Pattern");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 720);
        setMinimumSize(new Dimension(860, 620));
        setLocationRelativeTo(null);

        initLookAndFeel();
        buildUI();
        addSampleData();
        applyFilter();
    }

    private void initLookAndFeel() {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
        getContentPane().setBackground(new Color(245, 247, 250));
    }

    private void buildUI() {
        setLayout(new BorderLayout(12, 12));

        // Header
        JPanel header = buildHeader();
        add(header, BorderLayout.NORTH);

        // Center split: left = form + criteria, right = tables
        JSplitPane center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                buildLeftPanel(), buildRightPanel());
        center.setDividerLocation(320);
        center.setResizeWeight(0.3);
        center.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        add(center, BorderLayout.CENTER);

        // Status bar
        statusLabel = new JLabel("  Prêt.");
        statusLabel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(200, 205, 215)));
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(90, 100, 120));
        add(statusLabel, BorderLayout.SOUTH);
    }

    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(52, 73, 94));
        p.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("📋  Système de Sélection des Candidats");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Filtrage dynamique par critères d'éligibilité");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(new Color(189, 195, 199));

        JPanel text = new JPanel(new GridLayout(2, 1, 2, 2));
        text.setOpaque(false);
        text.add(title);
        text.add(subtitle);
        p.add(text, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildLeftPanel() {
        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setBackground(new Color(245, 247, 250));

        left.add(buildFormPanel());
        left.add(Box.createVerticalStrut(12));
        left.add(buildCriteriaPanel());
        left.add(Box.createVerticalGlue());
        return left;
    }

    // ---- Form ----
    private JPanel buildFormPanel() {
        JPanel panel = createCard("➕  Ajouter un Candidat");

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(4, 4, 4, 4);

        nameField   = new JTextField(14);
        ageField    = new JTextField(14);
        gradeField  = new JTextField(14);
        expField    = new JTextField(14);

        scholarshipField = new JCheckBox("Boursier");
        scholarshipField.setOpaque(false);

        String[][] rows = {{"Nom :", null}, {"Âge :", null}, {"Note /20 :", null}, {"Expérience (ans) :", null}};
        JTextField[] fields = {nameField, ageField, gradeField, expField};

        for (int i = 0; i < fields.length; i++) {
            gc.gridx = 0; gc.gridy = i; gc.weightx = 0;
            JLabel lbl = new JLabel(rows[i][0]);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
            form.add(lbl, gc);
            gc.gridx = 1; gc.weightx = 1;
            styleField(fields[i]);
            form.add(fields[i], gc);
        }

        gc.gridx = 0; gc.gridy = 4; gc.gridwidth = 2;
        scholarshipField.setFont(new Font("SansSerif", Font.PLAIN, 12));
        form.add(scholarshipField, gc);

        gc.gridy = 5;
        JButton addBtn = createButton("Ajouter le candidat", new Color(39, 174, 96));
        addBtn.addActionListener(e -> addCandidate());
        form.add(addBtn, gc);

        gc.gridy = 6;
        JButton clearBtn = createButton("Effacer la saisie", new Color(149, 165, 166));
        clearBtn.addActionListener(e -> clearForm());
        form.add(clearBtn, gc);

        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    // ---- Criteria ----
    private JPanel buildCriteriaPanel() {
        JPanel panel = createCard("🔍  Critères d'Éligibilité");

        JPanel checks = new JPanel(new GridLayout(4, 1, 4, 6));
        checks.setOpaque(false);

        cbAge        = createCriteriaCheckbox("✅  Âge ≥ 18 ans");
        cbGrade      = createCriteriaCheckbox("📊  Note ≥ 12 / 20");
        cbExperience = createCriteriaCheckbox("💼  Expérience ≥ 2 ans");
        cbScholarship= createCriteriaCheckbox("🎓  Boursier uniquement");

        checks.add(cbAge);
        checks.add(cbGrade);
        checks.add(cbExperience);
        checks.add(cbScholarship);

        ActionListener filterListener = e -> applyFilter();
        cbAge.addActionListener(filterListener);
        cbGrade.addActionListener(filterListener);
        cbExperience.addActionListener(filterListener);
        cbScholarship.addActionListener(filterListener);

        JButton resetBtn = createButton("Réinitialiser les critères", new Color(52, 152, 219));
        resetBtn.addActionListener(e -> {
            cbAge.setSelected(false); cbGrade.setSelected(false);
            cbExperience.setSelected(false); cbScholarship.setSelected(false);
            applyFilter();
        });

        panel.add(checks, BorderLayout.CENTER);
        panel.add(resetBtn, BorderLayout.SOUTH);
        return panel;
    }

    // ---- Tables ----
    private JPanel buildRightPanel() {
        JPanel right = new JPanel(new GridLayout(2, 1, 0, 12));
        right.setOpaque(false);

        String[] cols = {"Nom", "Âge", "Note", "Expérience (ans)", "Boursier"};

        // All candidates
        allModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        allTable = styleTable(new JTable(allModel));
        JPanel allPanel = createCard("👥  Tous les Candidats");
        allPanel.add(new JScrollPane(allTable), BorderLayout.CENTER);

        // Filtered
        filteredModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        filteredTable = styleTable(new JTable(filteredModel));
        filteredTable.setBackground(new Color(232, 248, 232));
        JPanel filteredPanel = createCard("✅  Candidats Retenus");
        filteredPanel.add(new JScrollPane(filteredTable), BorderLayout.CENTER);

        right.add(allPanel);
        right.add(filteredPanel);
        return right;
    }

    // ---- Logic ----
    private void addCandidate() {
        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Le nom est requis.");
            int age = Integer.parseInt(ageField.getText().trim());
            double grade = Double.parseDouble(gradeField.getText().trim());
            int exp = Integer.parseInt(expField.getText().trim());
            boolean scholarship = scholarshipField.isSelected();

            if (grade < 0 || grade > 20) throw new IllegalArgumentException("Note entre 0 et 20.");
            if (age < 0 || exp < 0) throw new IllegalArgumentException("Valeurs négatives non autorisées.");

            Candidate c = new Candidate(name, age, grade, exp, scholarship);
            candidates.add(c);
            allModel.addRow(toRow(c));
            clearForm();
            applyFilter();
            setStatus("✅ Candidat \"" + name + "\" ajouté avec succès.", new Color(39, 174, 96));
        } catch (NumberFormatException ex) {
            showError("Veuillez saisir des valeurs numériques valides pour l'âge, la note et l'expérience.");
        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void applyFilter() {
        // Build composite specification dynamically
        List<Specification<Candidate>> specs = new ArrayList<>();
        if (cbAge.isSelected())        specs.add(new AgeSpecification());
        if (cbGrade.isSelected())      specs.add(new GradeSpecification());
        if (cbExperience.isSelected()) specs.add(new ExperienceSpecification());
        if (cbScholarship.isSelected())specs.add(new ScholarshipSpecification());

        Specification<Candidate> combined = null;
        for (Specification<Candidate> s : specs) {
            combined = (combined == null) ? s : new AndSpecification<>(combined, s);
        }

        final Specification<Candidate> finalSpec = combined;
        filteredModel.setRowCount(0);
        int count = 0;
        for (Candidate c : candidates) {
            if (finalSpec == null || finalSpec.isSatisfiedBy(c)) {
                filteredModel.addRow(toRow(c));
                count++;
            }
        }

        int total = candidates.size();
        String criteriaInfo = specs.isEmpty() ? "aucun critère" : specs.size() + " critère(s) actif(s)";
        setStatus("  " + count + " / " + total + " candidat(s) retenu(s) — " + criteriaInfo + ".",
                new Color(52, 73, 94));
    }

    private Object[] toRow(Candidate c) {
        return new Object[]{c.getName(), c.getAge(), String.format("%.1f", c.getGrade()),
                c.getExperienceYears(), c.isHasScholarship() ? "Oui" : "Non"};
    }

    private void clearForm() {
        nameField.setText(""); ageField.setText(""); gradeField.setText("");
        expField.setText(""); scholarshipField.setSelected(false);
        nameField.requestFocus();
    }

    private void addSampleData() {
        Candidate[] samples = {
                new Candidate("Alice Martin",    22, 15.5, 3, true),
                new Candidate("Bob Dupont",      17, 13.0, 1, false),
                new Candidate("Clara Petit",     25, 11.0, 4, true),
                new Candidate("David Moreau",    19, 14.0, 2, false),
                new Candidate("Emma Bernard",    21, 16.5, 0, true),
                new Candidate("François Blanc",  30, 12.5, 5, false),
                new Candidate("Grace Lambert",   20, 9.0,  2, true),
                new Candidate("Hugo Girard",     23, 13.5, 3, false),
        };
        for (Candidate c : samples) {
            candidates.add(c);
            allModel.addRow(toRow(c));
        }
    }

    // ---- UI Helpers ----
    private JPanel createCard(String title) {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(218, 223, 230), 1, true),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        lbl.setForeground(new Color(52, 73, 94));
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
        panel.add(lbl, BorderLayout.NORTH);
        return panel;
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        btn.setOpaque(true);
        return btn;
    }

    private JCheckBox createCriteriaCheckbox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cb.setOpaque(false);
        cb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return cb;
    }

    private void styleField(JTextField f) {
        f.setFont(new Font("SansSerif", Font.PLAIN, 12));
        f.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(189, 195, 199), 1, true),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }

    private JTable styleTable(JTable t) {
        t.setFont(new Font("SansSerif", Font.PLAIN, 12));
        t.setRowHeight(26);
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        t.getTableHeader().setBackground(new Color(52, 73, 94));
        t.getTableHeader().setForeground(Color.BLACK);
        t.setSelectionBackground(new Color(174, 214, 241));
        t.setGridColor(new Color(220, 225, 230));
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(false);
        t.setFillsViewportHeight(true);
        return t;
    }

    private void setStatus(String msg, Color color) {
        statusLabel.setText(msg);
        statusLabel.setForeground(color);
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CandidateApp().setVisible(true));
    }
}