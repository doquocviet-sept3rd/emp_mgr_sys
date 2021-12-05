/*
 * Created by JFormDesigner on Tue Nov 23 19:09:56 PST 2021
 */

package views.Frames.dashboard;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import com.toedter.calendar.*;
import system.entities.*;
import system.services.*;
import system.services.impls.*;

/**
 * @author unknown
 */
public class JobHistoryFrame extends JFrame {
    private final IJobHistoryService jobHistoryService = new JobHistoryService();

    public JobHistoryFrame() {
        initComponents();
        createUIComponents();
        load();
    }

    private void createUIComponents() {

    }

    private void load() {
        initData(null);
        tblJobHistories.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void initData(List<JobHistory> src) {
        try {
            Object[] header = {
                    "id",
                    "employee",
                    "job",
                    "department",
                    "start date",
                    "end date"
            };
            Object[][] data = new Object[500][header.length];
            List<JobHistory> jobHistories;
            if (src == null) {
                jobHistories = jobHistoryService.findAll();
            } else {
                jobHistories = src;
            }
            for (int i = 0; i < jobHistories.size(); i++) {
                JobHistory jobHistory = jobHistories.get(i);
                data[i][0] = jobHistory.getId();
                data[i][1] = "[" + jobHistory.getEmployee().getId() + "] " + jobHistory.getEmployee().getName();
                data[i][2] = "[" + jobHistory.getJob().getId() + "] " + jobHistory.getJob().getTitle();
                data[i][3] = "[" + jobHistory.getDepartment().getId() + "] " + jobHistory.getDepartment().getName();
                data[i][4] = jobHistory.getStartDate();
                data[i][5] = jobHistory.getEndDate();
            }
            tblJobHistories.setModel(new DefaultTableModel(data, header));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tblJobHistoriesMouseClicked(MouseEvent e) {
        int row = tblJobHistories.rowAtPoint(e.getPoint());
        bindingFromTableToDetail(row);
    }

    private void bindingFromTableToDetail(int row) {
        try {
            txfId.setText(tblJobHistories.getModel().getValueAt(row, 0).toString());
            txfEmployee.setText(tblJobHistories.getModel().getValueAt(row, 1).toString());
            txfJob.setText(tblJobHistories.getModel().getValueAt(row, 2).toString());
            txfDept.setText(tblJobHistories.getModel().getValueAt(row, 3).toString());
            Timestamp start = Timestamp.valueOf(tblJobHistories.getModel().getValueAt(row, 4).toString());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(start.getTime());
            dChooserStart.setCalendar(cal);
            if (!Objects.equals(tblJobHistories.getModel().getValueAt(row, 5), null)) {
                Timestamp end = Timestamp.valueOf(tblJobHistories.getModel().getValueAt(row, 5).toString());
                cal.setTimeInMillis(end.getTime());
                dChooserEnd.setCalendar(cal);
            } else {
                dChooserEnd.setCalendar(null);
            }
        } catch (Exception e) {
            resetInput();
            e.printStackTrace();
        }
    }

    private void btnExitMouseClicked(MouseEvent e) {
        if (JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
            System.exit(0);
        }
    }

    private void resetInput() {
        txfId.setText("");
        txfEmployee.setText("");
        txfJob.setText("");
        txfDept.setText("");
        dChooserEnd.setCalendar(null);
        dChooserStart.setCalendar(null);
    }

    private void btnSearchMouseClicked(MouseEvent e) {
        String filter = String.valueOf(cboxFilter.getSelectedItem());
        initData(jobHistoryService.findAllByKey(txfSearch.getText(), filter));
        tblJobHistories.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Do Quoc Viet
        panel1 = new JPanel();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblJobHistories = new JTable();
        panel2 = new JPanel();
        label1 = new JLabel();
        txfId = new JTextField();
        label2 = new JLabel();
        txfEmployee = new JTextField();
        label10 = new JLabel();
        txfJob = new JTextField();
        label11 = new JLabel();
        txfDept = new JTextField();
        label12 = new JLabel();
        label13 = new JLabel();
        dChooserStart = new JDateChooser();
        dChooserEnd = new JDateChooser();
        txfSearch = new JTextField();
        btnSearch = new JButton();
        cboxFilter = new JComboBox<>();
        btnExit = new JButton();

        //======== this ========
        setTitle("Job History");
        setBackground(new Color(204, 204, 204));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(214, 217, 223));
            panel1.setBorder(new TitledBorder("Job histories information:"));
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "Dialo\u0067", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "borde\u0072" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(null);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel5.getComponentCount(); i++) {
                        Rectangle bounds = panel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel5.setMinimumSize(preferredSize);
                    panel5.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel5);
            panel5.setBounds(new Rectangle(new Point(20, 30), panel5.getPreferredSize()));

            //======== scrollPane1 ========
            {

                //---- tblJobHistories ----
                tblJobHistories.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, ""},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, "", null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "id", "employee", "job", "department", "start", "end"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, true, true, true, true, true
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                tblJobHistories.setBorder(new EtchedBorder());
                tblJobHistories.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblJobHistoriesMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblJobHistories);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(20, 30, 700, 295);
        }
        contentPane.add(panel1);
        panel1.setBounds(15, 55, 745, 380);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(214, 217, 223));
            panel2.setBorder(new TitledBorder("Detail:"));
            panel2.setLayout(null);

            //---- label1 ----
            label1.setText("Id:");
            label1.setBackground(new Color(204, 204, 204));
            panel2.add(label1);
            label1.setBounds(25, 30, 58, 16);

            //---- txfId ----
            txfId.setEditable(false);
            panel2.add(txfId);
            txfId.setBounds(100, 25, 220, 28);

            //---- label2 ----
            label2.setText("Employee:");
            panel2.add(label2);
            label2.setBounds(25, 65, 58, 16);

            //---- txfEmployee ----
            txfEmployee.setEditable(false);
            panel2.add(txfEmployee);
            txfEmployee.setBounds(100, 60, 220, 28);

            //---- label10 ----
            label10.setText("Job:");
            panel2.add(label10);
            label10.setBounds(25, 100, 80, 16);

            //---- txfJob ----
            txfJob.setEditable(false);
            panel2.add(txfJob);
            txfJob.setBounds(100, 95, 220, 28);

            //---- label11 ----
            label11.setText("Department:");
            panel2.add(label11);
            label11.setBounds(25, 135, 80, 16);

            //---- txfDept ----
            txfDept.setEditable(false);
            panel2.add(txfDept);
            txfDept.setBounds(100, 130, 220, 28);

            //---- label12 ----
            label12.setText("Start:");
            panel2.add(label12);
            label12.setBounds(25, 170, 80, 16);

            //---- label13 ----
            label13.setText("End:");
            panel2.add(label13);
            label13.setBounds(25, 205, 80, 16);

            //---- dChooserStart ----
            dChooserStart.setEnabled(false);
            panel2.add(dChooserStart);
            dChooserStart.setBounds(100, 165, 220, 28);

            //---- dChooserEnd ----
            dChooserEnd.setEnabled(false);
            panel2.add(dChooserEnd);
            dChooserEnd.setBounds(100, 200, 220, 28);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2);
        panel2.setBounds(775, 55, 345, 380);
        contentPane.add(txfSearch);
        txfSearch.setBounds(245, 15, 415, txfSearch.getPreferredSize().height);

        //---- btnSearch ----
        btnSearch.setText("Search");
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSearchMouseClicked(e);
            }
        });
        contentPane.add(btnSearch);
        btnSearch.setBounds(775, 15, 95, 28);

        //---- cboxFilter ----
        cboxFilter.setModel(new DefaultComboBoxModel<>(new String[] {
            "no filter",
            "id",
            "employee",
            "job",
            "department"
        }));
        contentPane.add(cboxFilter);
        cboxFilter.setBounds(665, 15, 105, 28);

        //---- btnExit ----
        btnExit.setText("Exit");
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnExitMouseClicked(e);
            }
        });
        contentPane.add(btnExit);
        btnExit.setBounds(1020, 15, 95, 28);

        contentPane.setPreferredSize(new Dimension(1140, 480));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Do Quoc Viet
    private JPanel panel1;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTable tblJobHistories;
    private JPanel panel2;
    private JLabel label1;
    private JTextField txfId;
    private JLabel label2;
    private JTextField txfEmployee;
    private JLabel label10;
    private JTextField txfJob;
    private JLabel label11;
    private JTextField txfDept;
    private JLabel label12;
    private JLabel label13;
    private JDateChooser dChooserStart;
    private JDateChooser dChooserEnd;
    private JTextField txfSearch;
    private JButton btnSearch;
    private JComboBox<String> cboxFilter;
    private JButton btnExit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
