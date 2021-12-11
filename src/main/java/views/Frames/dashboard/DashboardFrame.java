package views.Frames.dashboard;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.toedter.calendar.*;

import system.entities.*;
import system.utils.repositories.RepositoriesService;

/**
 * @author vcoder
 */
public class DashboardFrame extends JFrame {

    private int isAdd = 0;

    public DashboardFrame() {
        initComponents();
        createUIComponents();
        load();
    }

    private void createUIComponents() {
        txfAddress.setLineWrap(true);
        txfLogs.setLineWrap(true);
    }

    private void load() {
        initData(null);
        displayButton(true);
        displayInput(false);
        tblEmployee.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void initData(List<Employee> src) {
        try {
            Object[] header = {
                    "id",
                    "name",
                    "email",
                    "gender",
                    "phone",
                    "birth",
                    "hire date",
                    "salary",
                    "address",
                    "job",
                    "department"
            };
            Object[][] data = new Object[500][header.length];
            List<Employee> employees;
            if (src == null) {
                employees = RepositoriesService.getEmployeeService().findAll();
            } else {
                employees = src;
            }
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                data[i][0] = employee.getId();
                data[i][1] = employee.getName();
                data[i][2] = employee.getEmail();
                data[i][3] = employee.isGender() ? "Male" : "Female";
                data[i][4] = employee.getPhNumber();
                data[i][5] = employee.getBirthDate();
                data[i][6] = employee.getHireDate();
                data[i][7] = employee.getSalary();
                Ward ward = employee.getWard();
                data[i][8] = "[" + ward.getId() + "] " + "Ward: " + ward.getName() +
                        "\r\n[" + ward.getDistrict().getId() + "]" + " District: " + ward.getDistrict().getName() +
                        "\r\n[" + ward.getDistrict().getProvince().getId() + "]" + " Province: " + ward.getDistrict().getProvince().getName() +
                        "\r\n[" + ward.getDistrict().getProvince().getCountry().getId() + "]" + " Country: " + ward.getDistrict().getProvince().getCountry().getName() +
                        "\r\n[" + ward.getDistrict().getProvince().getCountry().getRegion().getId() + "]" + " Region: " + ward.getDistrict().getProvince().getCountry().getRegion().getName();
                data[i][9] = "[" + employee.getJob().getId() + "] " + employee.getJob().getTitle();
                data[i][10] = "[" + employee.getDepartment().getId() + "] " + employee.getDepartment().getName();
            }
            tblEmployee.setModel(new DefaultTableModel(data, header) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        } catch (Exception e) {
            txfLogs.setText(e + "\r\n" + txfLogs.getText());
        }
    }

    private void tblEmployeeMouseClicked(MouseEvent e) {
        if (isAdd == 0) {
            int row = tblEmployee.rowAtPoint(e.getPoint());
            bindingFromTableToDetail(row);
        }
    }

    private void bindingFromTableToDetail(int row) {
        try {
            txfId.setText(tblEmployee.getModel().getValueAt(row, 0).toString());
            txfName.setText(tblEmployee.getModel().getValueAt(row, 1).toString());
            txfEmail.setText(tblEmployee.getModel().getValueAt(row, 2).toString());
            boolean isMale = Objects.equals(tblEmployee.getModel().getValueAt(row, 3).toString(), "Male");
            if (isMale) {
                if (rdBtnFemale.isSelected()) {
                    rdBtnFemale.setSelected(false);
                }
                rdBtnMale.setSelected(true);
            } else {
                if (rdBtnMale.isSelected()) {
                    rdBtnMale.setSelected(false);
                }
                rdBtnFemale.setSelected(true);
            }
            txfPhone.setText(tblEmployee.getModel().getValueAt(row, 4).toString());
            Timestamp birth = Timestamp.valueOf(tblEmployee.getModel().getValueAt(row, 5).toString());
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(birth.getTime());
            dChooserHireDate.setCalendar(cal);
            Timestamp hireDate = Timestamp.valueOf(tblEmployee.getModel().getValueAt(row, 6).toString());
            cal.setTimeInMillis(hireDate.getTime());
            dChooserBirth.setCalendar(cal);
            txfSalary.setText(tblEmployee.getModel().getValueAt(row, 7).toString());
            txfAddress.setText(tblEmployee.getModel().getValueAt(row, 8).toString());
            txfJob.setText(tblEmployee.getModel().getValueAt(row, 9).toString());
            txfDept.setText(tblEmployee.getModel().getValueAt(row, 10).toString());
        } catch (Exception e) {
            resetInput();
            txfLogs.setText("Row is undefine" + "\r\n" + txfLogs.getText());
        }
    }

    private void rdBtnMaleMouseClicked(MouseEvent e) {
        if (rdBtnFemale.isSelected()) {
            rdBtnFemale.setSelected(false);
        }
        rdBtnMale.setSelected(true);
    }

    private void rdBtnFemaleMouseClicked(MouseEvent e) {
        if (rdBtnMale.isSelected()) {
            rdBtnMale.setSelected(false);
        }
        rdBtnFemale.setSelected(true);
    }

    private void btnExitMouseClicked(MouseEvent e) {
        if (JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
            System.exit(0);
        }
    }

    private void btnAddMouseClicked(MouseEvent e) {
        try {
            displayInput(true);
            displayButton(false);
            resetInput();
            isAdd = 1;
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(Timestamp.from(Instant.now()).getTime());
            dChooserHireDate.setCalendar(cal);
        } catch (Exception log) {
            //
        }
    }

    private void btnUpdateMouseClicked(MouseEvent e) {
        if (!txfId.getText().equals("")) {
            displayInput(true);
            displayButton(false);
            isAdd = -1;
        } else {
            JOptionPane.showMessageDialog(this, "Row is undefine!", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnDeleteMouseClicked(MouseEvent e) {
        if (!txfId.getText().equals("")) {
            if (JOptionPane.showConfirmDialog(this, "Do you want to delete this employee?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                if (RepositoriesService.getEmployeeService().delete(RepositoriesService.getEmployeeService().findOne(Long.parseLong(txfId.getText())))) {
                    JOptionPane.showMessageDialog(this, "success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                    load();
                } else {
                    JOptionPane.showMessageDialog(this, "Fail!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Row is undefine!", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void displayInput(boolean isDisplay) {
        txfName.setEditable(isDisplay);
        txfEmail.setEditable(isDisplay);
        rdBtnFemale.setEnabled(isDisplay);
        rdBtnMale.setEnabled(isDisplay);
        txfSalary.setEditable(isDisplay);
        txfPhone.setEditable(isDisplay);
        dChooserBirth.setEnabled(isDisplay);
        dChooserHireDate.setEnabled(isDisplay);
        tblEmployee.setEnabled(!isDisplay);
        txfJob.setCursor(Cursor.getPredefinedCursor(isDisplay ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        txfDept.setCursor(Cursor.getPredefinedCursor(isDisplay ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
        txfAddress.setCursor(Cursor.getPredefinedCursor(isDisplay ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR));
    }

    private void displayButton(boolean isDisplay) {
        btnAdd.setEnabled(isDisplay);
        btnUpdate.setEnabled(isDisplay);
        btnDelete.setEnabled(isDisplay);
        btnSave.setEnabled(!isDisplay);
        btnCancel.setEnabled(!isDisplay);
        btnReload.setEnabled(isDisplay);
    }

    private void resetInput() {
        txfId.setText("");
        txfName.setText("");
        txfEmail.setText("");
        rdBtnMale.setSelected(true);
        rdBtnFemale.setSelected(false);
        txfSalary.setText("");
        txfPhone.setText("");
        txfJob.setText("");
        txfDept.setText("");
        txfAddress.setText("");
    }

    private void btnSaveMouseClicked(MouseEvent e) {
        try {

            String name = txfName.getText().equals("") ? null : txfName.getText();
            String email = txfEmail.getText().equals("") ? null : txfEmail.getText();
            boolean isMale = rdBtnMale.isSelected();
            double salary = Double.parseDouble(txfSalary.getText());
            String phone = txfPhone.getText().equals("") ? null : txfPhone.getText();
            Timestamp hireDate = new Timestamp(dChooserHireDate.getDate().getTime());
            Timestamp birthDate = new Timestamp(dChooserBirth.getDate().getTime());
            long jobId = getIdFromString(txfJob.getText());
            long departmentId = getIdFromString(txfDept.getText());
            long wardId = getIdFromString(txfAddress.getText());

            if (isAdd == 1) {
                Long employeeId = RepositoriesService.getEmployeeService().insert(new Employee(name, email, isMale, birthDate, hireDate, phone, salary, jobId, departmentId, wardId));
                if (employeeId != null) {
                    // add job history
                    if (RepositoriesService.getJobHistoryService().insert(new JobHistory(
                            employeeId,
                            jobId,
                            departmentId,
                            Timestamp.from(Instant.now()),
                            null
                    )) != null) {
                        JOptionPane.showMessageDialog(this, "Success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        load();
                        isAdd = 0;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Fail!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                long employeeId = Long.parseLong(txfId.getText());
                Employee employeePrevious = RepositoriesService.getEmployeeService().findOne(employeeId);
                long jobIdPrevious = employeePrevious.getJobId();
                long departmentIdPrevious = employeePrevious.getDepartmentId();
                if (RepositoriesService.getEmployeeService().update(new Employee(employeeId, name, email, isMale, birthDate, hireDate, phone, salary, jobId, departmentId, wardId))) {
                    if (jobIdPrevious != jobId || departmentIdPrevious != departmentId) {
                        JobHistory jobHistoryPrevious = RepositoriesService.getJobHistoryService().findOnePrevious(employeeId);
                        jobHistoryPrevious.setEndDate(Timestamp.from(Instant.now()));
                        if (RepositoriesService.getJobHistoryService().update(jobHistoryPrevious)) {
                            if (RepositoriesService.getJobHistoryService().insert(new JobHistory(employeeId, jobId, departmentId, Timestamp.from(Instant.now()), null)) != null) {
                                JOptionPane.showMessageDialog(this, "Success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                                load();
                                isAdd = 0;
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Success!", "Information", JOptionPane.INFORMATION_MESSAGE);
                        load();
                        isAdd = 0;
                    }
                }
            }
        } catch (Exception log) {
            JOptionPane.showMessageDialog(this, log);
        }
    }

    private void btnCancelMouseClicked(MouseEvent e) {
        isAdd = 0;
        load();
    }

    private void txfJobMouseClicked(MouseEvent e) {
        if (isAdd != 0) {
            try {
                List<Job> jobs = RepositoriesService.getJobService().findAll();
                Object[] possibilities = new Object[jobs.size()];
                int index = 0;
                long jobIdCurrent = getIdFromString(Objects.equals(txfJob.getText(), "") ? "[-1]" : txfJob.getText());
                for (int i = 0; i < jobs.size(); i++) {
                    Job job = jobs.get(i);
                    possibilities[i] = "[" + job.getId() + "] " + job.getTitle();
                    if (job.getId() == jobIdCurrent) {
                        index = i;
                    }
                }
                String result = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a job!",
                        "Job",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[index]
                );
                Job job = RepositoriesService.getJobService().findOne(getIdFromString(result));
                double salary = Double.parseDouble(Objects.equals(txfSalary.getText(), "") ? "-1" : txfSalary.getText());
                if (job.getMinSalary() > salary || job.getMaxSalary() < salary) {
                    JOptionPane.showMessageDialog(this, "For this job, the salary must be greater than " + job.getMinSalary() + "$ and less than " + job.getMaxSalary() + "$",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (!Objects.equals(result, ""))
                    txfJob.setText(result);
            } catch (Exception log) {
                txfLogs.setText(log + "\r\n" + txfLogs.getText());
            }
        }
    }

    private void txfDeptMouseClicked(MouseEvent e) {
        if (isAdd != 0) {
            try {
                List<Department> departments = RepositoriesService.getDepartmentService().findAll();
                Object[] possibilities = new Object[departments.size()];
                int index = 0;
                long deptIdCurrent = getIdFromString(Objects.equals(txfDept.getText(), "") ? "[-1]" : txfDept.getText());
                for (int i = 0; i < departments.size(); i++) {
                    Department department = departments.get(i);
                    possibilities[i] = "[" + department.getId() + "] " + department.getName();
                    if (department.getId() == deptIdCurrent) {
                        index = i;
                    }
                }
                String result = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a department!",
                        "Department",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[index]
                );
                if (!Objects.equals(result, null)) {
                    txfDept.setText(result);
                }
            } catch (Exception log) {
                txfLogs.setText(log + "\r\n" + txfLogs.getText());
            }
        }
    }

    private void txfAddressMouseClicked(MouseEvent e) {
        if (isAdd != 0) {
            try {
                List<Region> regions = RepositoriesService.getRegionService().findAll();
                Object[] possibilities = new Object[regions.size()];
                for (int i = 0; i < regions.size(); i++) {
                    Region region = regions.get(i);
                    possibilities[i] = "[" + region.getId() + "] " + region.getName();
                }
                String region = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a region!",
                        "Region",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                List<Country> countries = RepositoriesService.getCountryService().findAllByRegion(getIdFromString(region));
                possibilities = new Object[countries.size()];
                for (int i = 0; i < countries.size(); i++) {
                    Country country = countries.get(i);
                    possibilities[i] = "[" + country.getId() + "] " + country.getName();
                }
                String country = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a country!",
                        "Country",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                List<Province> provinces = RepositoriesService.getProvinceService().findAllByCountry(getIdFromString(country));
                possibilities = new Object[provinces.size()];
                for (int i = 0; i < provinces.size(); i++) {
                    Province province = provinces.get(i);
                    possibilities[i] = "[" + province.getId() + "] " + province.getName();
                }
                String province = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a province!",
                        "Province",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                List<District> districts = RepositoriesService.getDistrictService().findAllByProvince(getIdFromString(province));
                possibilities = new Object[districts.size()];
                for (int i = 0; i < districts.size(); i++) {
                    District district = districts.get(i);
                    possibilities[i] = "[" + district.getId() + "] " + district.getName();
                }
                String district = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a district!",
                        "District",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );

                List<Ward> wards = RepositoriesService.getWardService().findAllByDistrict(getIdFromString(district));
                possibilities = new Object[wards.size()];
                for (int i = 0; i < wards.size(); i++) {
                    Ward ward = wards.get(i);
                    possibilities[i] = "[" + ward.getId() + "] " + ward.getName();
                }
                String ward = (String) JOptionPane.showInputDialog(
                        this,
                        "Please choose a ward!",
                        "Ward",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        possibilities[0]
                );
                String result = ward +
                        "\r\n" + district +
                        "\r\n" + province +
                        "\r\n" + country +
                        "\r\n" + region;
                txfAddress.setText(result);
            } catch (Exception log) {
                txfLogs.setText(log + "\r\n" + txfLogs.getText());
            }
        }
    }

    private long getIdFromString(String str) {
        return Long.parseLong(str.substring(1, str.indexOf(']')));
    }

    private void btnSearchMouseClicked(MouseEvent e) {
        String filter = String.valueOf(cboxFilter.getSelectedItem());
        initData(RepositoriesService.getEmployeeService().findAllByKey(txfSearch.getText(), filter));
        displayButton(true);
        displayInput(false);
        tblEmployee.setRowSelectionInterval(0, 0);
        bindingFromTableToDetail(0);
    }

    private void btnReloadMouseClicked(MouseEvent e) {
        load();
    }

    private void mnDeptMouseClicked(MouseEvent e) {
        new DepartmentFrame().setVisible(true);
    }

    private void mnJobMouseClicked(MouseEvent e) {
        new JobFrame().setVisible(true);
    }

    private void mnJobHisMouseClicked(MouseEvent e) {
        new JobHistoryFrame().setVisible(true);
    }

    private void txfSalaryFocusLost(FocusEvent e) {
        if (isAdd == -1) {
            // update
            double salary = Double.parseDouble(txfSalary.getText());
            Employee employee = RepositoriesService.getEmployeeService().findOne(Long.parseLong(txfId.getText()));
            if (salary < employee.getJob().getMinSalary() || employee.getJob().getMaxSalary() < salary) {
                JOptionPane.showMessageDialog(this, "For this job, the salary must be greater than " + employee.getJob().getMinSalary() + "$ and less than " + employee.getJob().getMaxSalary() + "$",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                txfSalary.setText(tblEmployee.getModel().getValueAt(tblEmployee.getSelectedRow(), 7).toString());
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Do Quoc Viet
        mnBarMain = new JMenuBar();
        mnDept = new JMenu();
        mnJob = new JMenu();
        mnJobHis = new JMenu();
        pnlEmployeesInfo = new JPanel();
        panel5 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblEmployee = new JTable();
        pnlDetail = new JPanel();
        label1 = new JLabel();
        txfId = new JTextField();
        label2 = new JLabel();
        txfName = new JTextField();
        label3 = new JLabel();
        txfEmail = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        txfSalary = new JTextField();
        label6 = new JLabel();
        txfPhone = new JTextField();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        txfJob = new JTextField();
        label10 = new JLabel();
        txfDept = new JTextField();
        label11 = new JLabel();
        rdBtnMale = new JRadioButton();
        rdBtnFemale = new JRadioButton();
        dChooserHireDate = new JDateChooser();
        dChooserBirth = new JDateChooser();
        scrollPane2 = new JScrollPane();
        txfAddress = new JTextArea();
        pnlControls = new JPanel();
        btnAdd = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        btnSave = new JButton();
        btnCancel = new JButton();
        btnReload = new JButton();
        txfSearch = new JTextField();
        btnSearch = new JButton();
        pnlLogs = new JPanel();
        scrollPane3 = new JScrollPane();
        txfLogs = new JTextArea();
        cboxFilter = new JComboBox<>();
        btnExit = new JButton();

        //======== this ========
        setTitle("Dashboard");
        setBackground(new Color(204, 204, 204));
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== mnBarMain ========
        {

            //======== mnDept ========
            {
                mnDept.setText("Department");
                mnDept.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                mnDept.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mnDeptMouseClicked(e);
                    }
                });
            }
            mnBarMain.add(mnDept);

            //======== mnJob ========
            {
                mnJob.setText("Job");
                mnJob.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                mnJob.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mnJobMouseClicked(e);
                    }
                });
            }
            mnBarMain.add(mnJob);

            //======== mnJobHis ========
            {
                mnJobHis.setText("Job History");
                mnJobHis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                mnJobHis.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mnJobHisMouseClicked(e);
                    }
                });
            }
            mnBarMain.add(mnJobHis);
        }
        setJMenuBar(mnBarMain);

        //======== pnlEmployeesInfo ========
        {
            pnlEmployeesInfo.setBackground(new Color(214, 217, 223));
            pnlEmployeesInfo.setBorder(new TitledBorder("Employees information:"));
            pnlEmployeesInfo.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
                    border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border.TitledBorder.CENTER
                    , javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font
                    .BOLD, 12), java.awt.Color.red), pnlEmployeesInfo.getBorder()));
            pnlEmployeesInfo.addPropertyChangeListener(
                    new java.beans.PropertyChangeListener() {
                        @Override
                        public void propertyChange(java.beans.PropertyChangeEvent e) {
                            if ("\u0062ord\u0065r"
                                    .equals(e.getPropertyName())) throw new RuntimeException();
                        }
                    });
            pnlEmployeesInfo.setLayout(null);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel5.getComponentCount(); i++) {
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
            pnlEmployeesInfo.add(panel5);
            panel5.setBounds(new Rectangle(new Point(20, 30), panel5.getPreferredSize()));

            //======== scrollPane1 ========
            {

                //---- tblEmployee ----
                tblEmployee.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                tblEmployee.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tblEmployeeMouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(tblEmployee);
            }
            pnlEmployeesInfo.add(scrollPane1);
            scrollPane1.setBounds(20, 30, 700, 300);
        }
        contentPane.add(pnlEmployeesInfo);
        pnlEmployeesInfo.setBounds(15, 55, 745, 380);

        //======== pnlDetail ========
        {
            pnlDetail.setBackground(new Color(214, 217, 223));
            pnlDetail.setBorder(new TitledBorder("Detail:"));
            pnlDetail.setLayout(null);

            //---- label1 ----
            label1.setText("Id:");
            label1.setBackground(new Color(204, 204, 204));
            pnlDetail.add(label1);
            label1.setBounds(25, 30, 58, 16);

            //---- txfId ----
            txfId.setDoubleBuffered(true);
            txfId.setEditable(false);
            pnlDetail.add(txfId);
            txfId.setBounds(100, 25, 220, 28);

            //---- label2 ----
            label2.setText("Name:");
            pnlDetail.add(label2);
            label2.setBounds(25, 65, 58, 16);

            //---- txfName ----
            txfName.setDoubleBuffered(true);
            txfName.setEditable(false);
            pnlDetail.add(txfName);
            txfName.setBounds(100, 60, 220, 28);

            //---- label3 ----
            label3.setText("Email:");
            pnlDetail.add(label3);
            label3.setBounds(25, 100, 58, 16);

            //---- txfEmail ----
            txfEmail.setDoubleBuffered(true);
            txfEmail.setEditable(false);
            pnlDetail.add(txfEmail);
            txfEmail.setBounds(100, 95, 220, 28);

            //---- label4 ----
            label4.setText("Gender:");
            pnlDetail.add(label4);
            label4.setBounds(25, 135, 58, 16);

            //---- label5 ----
            label5.setText("Salary:");
            pnlDetail.add(label5);
            label5.setBounds(25, 170, 58, 16);

            //---- txfSalary ----
            txfSalary.setDoubleBuffered(true);
            txfSalary.setEditable(false);
            txfSalary.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    txfSalaryFocusLost(e);
                }
            });
            pnlDetail.add(txfSalary);
            txfSalary.setBounds(100, 165, 220, 28);

            //---- label6 ----
            label6.setText("PhNo:");
            pnlDetail.add(label6);
            label6.setBounds(25, 205, 58, 16);

            //---- txfPhone ----
            txfPhone.setDoubleBuffered(true);
            txfPhone.setEditable(false);
            pnlDetail.add(txfPhone);
            txfPhone.setBounds(100, 200, 220, 28);

            //---- label7 ----
            label7.setText("Hire date:");
            pnlDetail.add(label7);
            label7.setBounds(25, 240, 58, 16);

            //---- label8 ----
            label8.setText("Birth:");
            pnlDetail.add(label8);
            label8.setBounds(25, 275, 58, 16);

            //---- label9 ----
            label9.setText("Job:");
            pnlDetail.add(label9);
            label9.setBounds(25, 310, 75, 16);

            //---- txfJob ----
            txfJob.setDoubleBuffered(true);
            txfJob.setEditable(false);
            txfJob.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    txfJobMouseClicked(e);
                }
            });
            pnlDetail.add(txfJob);
            txfJob.setBounds(100, 305, 220, 28);

            //---- label10 ----
            label10.setText("Department:");
            pnlDetail.add(label10);
            label10.setBounds(25, 345, 80, 16);

            //---- txfDept ----
            txfDept.setDoubleBuffered(true);
            txfDept.setEditable(false);
            txfDept.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    txfDeptMouseClicked(e);
                }
            });
            pnlDetail.add(txfDept);
            txfDept.setBounds(100, 340, 220, 28);

            //---- label11 ----
            label11.setText("Address:");
            pnlDetail.add(label11);
            label11.setBounds(25, 380, 80, 16);

            //---- rdBtnMale ----
            rdBtnMale.setText("Male");
            rdBtnMale.setEnabled(false);
            rdBtnMale.setSelected(true);
            rdBtnMale.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rdBtnMaleMouseClicked(e);
                }
            });
            pnlDetail.add(rdBtnMale);
            rdBtnMale.setBounds(100, 135, 100, rdBtnMale.getPreferredSize().height);

            //---- rdBtnFemale ----
            rdBtnFemale.setText("Female");
            rdBtnFemale.setEnabled(false);
            rdBtnFemale.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rdBtnFemaleMouseClicked(e);
                }
            });
            pnlDetail.add(rdBtnFemale);
            rdBtnFemale.setBounds(220, 135, 100, 18);

            //---- dChooserHireDate ----
            dChooserHireDate.setEnabled(false);
            pnlDetail.add(dChooserHireDate);
            dChooserHireDate.setBounds(100, 235, 220, 28);

            //---- dChooserBirth ----
            dChooserBirth.setEnabled(false);
            pnlDetail.add(dChooserBirth);
            dChooserBirth.setBounds(100, 270, 220, 28);

            //======== scrollPane2 ========
            {

                //---- txfAddress ----
                txfAddress.setEditable(false);
                txfAddress.setWrapStyleWord(true);
                txfAddress.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        txfAddressMouseClicked(e);
                    }
                });
                scrollPane2.setViewportView(txfAddress);
            }
            pnlDetail.add(scrollPane2);
            scrollPane2.setBounds(100, 375, 220, 115);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < pnlDetail.getComponentCount(); i++) {
                    Rectangle bounds = pnlDetail.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = pnlDetail.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                pnlDetail.setMinimumSize(preferredSize);
                pnlDetail.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(pnlDetail);
        pnlDetail.setBounds(775, 55, 345, 515);

        //======== pnlControls ========
        {
            pnlControls.setBorder(new TitledBorder("Controls:"));
            pnlControls.setBackground(new Color(214, 217, 223));
            pnlControls.setLayout(null);

            //---- btnAdd ----
            btnAdd.setText("Add");
            btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnAdd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnAddMouseClicked(e);
                }
            });
            pnlControls.add(btnAdd);
            btnAdd.setBounds(20, 35, 95, 28);

            //---- btnUpdate ----
            btnUpdate.setText("Update");
            btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnUpdate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnUpdateMouseClicked(e);
                }
            });
            pnlControls.add(btnUpdate);
            btnUpdate.setBounds(128, 35, 95, 28);

            //---- btnDelete ----
            btnDelete.setText("Delete");
            btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnDelete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnDeleteMouseClicked(e);
                }
            });
            pnlControls.add(btnDelete);
            btnDelete.setBounds(235, 35, 95, 28);

            //---- btnSave ----
            btnSave.setText("Save");
            btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnSave.setEnabled(false);
            btnSave.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnSaveMouseClicked(e);
                }
            });
            pnlControls.add(btnSave);
            btnSave.setBounds(20, 75, 95, 28);

            //---- btnCancel ----
            btnCancel.setText("Cancel");
            btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnCancel.setEnabled(false);
            btnCancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnCancelMouseClicked(e);
                }
            });
            pnlControls.add(btnCancel);
            btnCancel.setBounds(128, 75, 95, 28);

            //---- btnReload ----
            btnReload.setText("Reload");
            btnReload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnReload.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    btnReloadMouseClicked(e);
                }
            });
            pnlControls.add(btnReload);
            btnReload.setBounds(235, 75, 95, 28);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < pnlControls.getComponentCount(); i++) {
                    Rectangle bounds = pnlControls.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = pnlControls.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                pnlControls.setMinimumSize(preferredSize);
                pnlControls.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(pnlControls);
        pnlControls.setBounds(410, 445, 350, 125);
        contentPane.add(txfSearch);
        txfSearch.setBounds(245, 15, 415, txfSearch.getPreferredSize().height);

        //---- btnSearch ----
        btnSearch.setText("Search");
        btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSearchMouseClicked(e);
            }
        });
        contentPane.add(btnSearch);
        btnSearch.setBounds(775, 15, 95, 28);

        //======== pnlLogs ========
        {
            pnlLogs.setBorder(new TitledBorder("Logs:"));
            pnlLogs.setLayout(null);

            //======== scrollPane3 ========
            {

                //---- txfLogs ----
                txfLogs.setEditable(false);
                scrollPane3.setViewportView(txfLogs);
            }
            pnlLogs.add(scrollPane3);
            scrollPane3.setBounds(15, 30, 350, 80);
        }
        contentPane.add(pnlLogs);
        pnlLogs.setBounds(15, 445, 380, 125);

        //---- cboxFilter ----
        cboxFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cboxFilter.setModel(new DefaultComboBoxModel<>(new String[]{
                "no filter",
                "id",
                "name",
                "email",
                "address",
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
        btnExit.setBounds(1025, 15, 95, 28);

        contentPane.setPreferredSize(new Dimension(1140, 645));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Do Quoc Viet
    private JMenuBar mnBarMain;
    private JMenu mnDept;
    private JMenu mnJob;
    private JMenu mnJobHis;
    private JPanel pnlEmployeesInfo;
    private JPanel panel5;
    private JScrollPane scrollPane1;
    private JTable tblEmployee;
    private JPanel pnlDetail;
    private JLabel label1;
    private JTextField txfId;
    private JLabel label2;
    private JTextField txfName;
    private JLabel label3;
    private JTextField txfEmail;
    private JLabel label4;
    private JLabel label5;
    private JTextField txfSalary;
    private JLabel label6;
    private JTextField txfPhone;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField txfJob;
    private JLabel label10;
    private JTextField txfDept;
    private JLabel label11;
    private JRadioButton rdBtnMale;
    private JRadioButton rdBtnFemale;
    private JDateChooser dChooserHireDate;
    private JDateChooser dChooserBirth;
    private JScrollPane scrollPane2;
    private JTextArea txfAddress;
    private JPanel pnlControls;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnReload;
    private JTextField txfSearch;
    private JButton btnSearch;
    private JPanel pnlLogs;
    private JScrollPane scrollPane3;
    private JTextArea txfLogs;
    private JComboBox<String> cboxFilter;
    private JButton btnExit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
