package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Hệ thống quản lý điểm sinh viên - D19CNPM3 - EPU");
        setSize(1020, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        StudentPanel studentPanel = new StudentPanel();
        SubjectPanel subjectPanel = new SubjectPanel();

        tabbedPane.addTab("Sinh viên", studentPanel);
        tabbedPane.addTab("Môn học", subjectPanel);

        tabbedPane.addChangeListener(e -> {
            Component selected = tabbedPane.getSelectedComponent();
            if (selected == studentPanel) {
                studentPanel.refreshData();
            } else if (selected == subjectPanel) {
                subjectPanel.refreshData();
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }
}
