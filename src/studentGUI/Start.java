package studentGUI;


import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static studentGUI.XMLUtils.XML2list;
import static studentGUI.XMLUtils.list2XML;

public class Start {
    private JFrame frame;
    private Student student;
    private static List<Student> STUDENTS = new ArrayList<Student>();
    private static String FILEPATH = "";


    public Start() {
        student = new Student();
        frame = new JFrame("STUDENT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(3000, 2000);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1, 1, 1));    //为Frame窗口设置布局为BorderLayout
        frame.setJMenuBar(menuBar());
        frame.add(topPanel());
        frame.add(bottomPanel());
        frame.setBounds(300, 200, 600, 300);
        frame.setVisible(true);
    }

    public JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        return menuBar;
    }


    public JPanel topPanel() {
        JPanel p = new JPanel();
        GridBagLayout bagLayout = new GridBagLayout();    //创建GridBagLayout布局管理器
        GridBagConstraints constraints = new GridBagConstraints();
        p.setLayout(bagLayout);    //使用GridBagLayout布局管理器
        constraints.fill = GridBagConstraints.BOTH;    //组件填充显示区域
        constraints.weightx = 0.0;    //恢复默认值
        JLabel nameLabel = new JLabel("姓名:");
        JTextField nameTextField = new JTextField(8);
        JLabel genderLabel = new JLabel("性别:");
        JComboBox genderBox = new JComboBox();    //创建JComboBox
        JLabel birthLabel = new JLabel("出生日期:");
        JComboBox ageYearBox = new JComboBox();    //创建JComboBox
        JLabel yearLabel = new JLabel("年");
        JComboBox monthBox = new JComboBox();    //创建JComboBox
        JLabel monthLabel = new JLabel("月");
        JComboBox dayBox = new JComboBox();    //创建JComboBox
        JLabel dayLabel = new JLabel("日");
        JTextField idTextField = new JTextField(10);
        JLabel idLabel = new JLabel("学号：");
        JTextField classTextField = new JTextField(10);
        JLabel classLabel = new JLabel("班级：");
        JTextField schoolTextField = new JTextField(15);
        JLabel schoolLabel = new JLabel("学校：");
        JTextArea otherTextField = new JTextArea();
        JLabel otherLabel = new JLabel("备注：");
        JPanel birthPanel = new JPanel();
        birthPanel.setLayout(bagLayout);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        p.add(nameLabel, constraints);

        constraints.gridx = 6;
        constraints.gridwidth = 9;
        p.add(nameTextField, constraints);


        constraints.gridx = 0;
        constraints.gridwidth = 4;
        //bagLayout.setConstraints(nameLabel, constraints);
        birthPanel.add(birthLabel, constraints);

        for (int i = Calendar.getInstance().get(Calendar.YEAR); i > 1900; i--) {
            ageYearBox.addItem(i);
        }
        ageYearBox.setSelectedItem(2000);
        constraints.gridx = 5;
        constraints.gridwidth = 4;
        birthPanel.add(ageYearBox, constraints);

        constraints.gridx = 10;
        constraints.gridwidth = 4;
        birthPanel.add(yearLabel, constraints);

        for (int i = 1; i < 13; i++) {
            monthBox.addItem(i);
        }
        monthBox.setSelectedItem(1);
        constraints.gridx = 15;
        constraints.gridwidth = 2;
        birthPanel.add(monthBox, constraints);


        constraints.gridx = 18;
        constraints.gridwidth = 4;
        birthPanel.add(monthLabel, constraints);

        for (int i = 1; i < 32; i++) {
            dayBox.addItem(i);
        }
        dayBox.setSelectedItem(1);
        constraints.gridx = 23;
        constraints.gridwidth = 2;
        student.setBirth(2000, 1, 1);
        birthPanel.add(dayBox, constraints);

        constraints.gridx = 26;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        birthPanel.add(dayLabel, constraints);


        constraints.gridx = 15;
        constraints.gridy = 0;
        constraints.gridwidth = 29;
        p.add(birthPanel, constraints);

        constraints.gridx = 45;
        constraints.gridwidth = 1;
        p.add(new JPanel(), constraints);

        constraints.gridx = 46;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        p.add(genderLabel, constraints);


        genderBox.addItem("MAN");
        genderBox.addItem("FAMAN");
        genderBox.addItem("OTHER");
        student.setGender((String) genderBox.getSelectedItem());
        constraints.gridx = 50;
        constraints.gridy = 0;
        constraints.gridwidth = 0;
        p.add(genderBox, constraints);


        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        p.add(schoolLabel, constraints);


        constraints.gridx = 6;
        constraints.gridwidth = 15;
        p.add(schoolTextField, constraints);

        constraints.gridx = 22;
        constraints.gridwidth = 3;
        p.add(classLabel, constraints);

        constraints.gridx = 26;
        constraints.gridwidth = 15;
        p.add(classTextField, constraints);

        constraints.gridx = 42;
        //constraints.gridwidth = 3;
        p.add(idLabel, constraints);

        constraints.gridx = 50;
        p.add(idTextField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.gridheight = 4;
        p.add(otherLabel, constraints);

        constraints.gridy = 1;
        constraints.gridheight = 1;
        p.add(new JPanel(), constraints);
        constraints.gridy = 3;
        p.add(new JPanel(), constraints);


        constraints.gridx = 6;
        constraints.gridy = 4;
        constraints.gridwidth = 0;
        constraints.gridheight = 4;
        otherTextField.setRows(4);
        otherTextField.setColumns(30);
        otherTextField.setLineWrap(true);
        otherTextField.setAutoscrolls(true);
        p.add(otherTextField, constraints);
        constraints.gridy = 9;
        JButton button = new JButton("插入");
        button.setBackground(Color.white);


        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue(e);
            }

            private void updateValue(DocumentEvent e) {
                Document st = e.getDocument();
                try {
                    String cmd = st.getText(0, st.getLength());
                    System.out.println("insert" + cmd);
                    student.setName(cmd);
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }
        });

        ageYearBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int y = (int) ageYearBox.getSelectedItem();
                int m = (int) monthBox.getSelectedItem();
                int d = (int) dayBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                dayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    dayBox.addItem(i);
                }
                student.setBirth(y, m , d);
            }
        });

        monthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = (int) monthBox.getSelectedItem();
                int y = (int) ageYearBox.getSelectedItem();
                Calendar calendar = Calendar.getInstance();
                calendar.set(y - 1900, m - 1, 1);
                calendar.roll(Calendar.DATE, -1);
                int max = calendar.get(Calendar.DATE);
                //dayBox = new JComboBox();
                dayBox.removeAllItems();
                for (int i = 1; i <= max; i++) {
                    dayBox.addItem(i);
                }
                student.setBirth(y, m, 1);

            }
        });

        dayBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int y = (int) ageYearBox.getSelectedItem();
                int m = (int) monthBox.getSelectedItem();
                int d = dayBox.getSelectedItem() == null ? 1 : (int) dayBox.getSelectedItem();
                student.setBirth(y, m, d);
                System.out.println("birth set as" + y + " " + m + " " + d) ;
            }
        });

        genderBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                student.setGender((String) genderBox.getSelectedItem());
            }
        });

        schoolTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue(e);
            }

            private void updateValue(DocumentEvent e) {
                Document st = e.getDocument();
                try {
                    String cmd = st.getText(0, st.getLength());
                    System.out.println("insert" + cmd);
                    student.setSchool(cmd);
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }
        });

        classTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue(e);
            }

            private void updateValue(DocumentEvent e) {
                Document st = e.getDocument();
                try {
                    String cmd = st.getText(0, st.getLength());
                    System.out.println("insert" + cmd);
                    student.setStudentClass(cmd);
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }
        });


        idTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateValue(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateValue(e);
            }

            private void updateValue(DocumentEvent e) {

                Document st = e.getDocument();
                try {
                    String cmd = st.getText(0, st.getLength());
                    System.out.println("insert" + cmd);
                    student.setStudentID(cmd);
                } catch (BadLocationException badLocationException) {
                    badLocationException.printStackTrace();
                }
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (allFilled().length() != 0) {
                    JOptionPane.showMessageDialog(null, "请输入：" + allFilled() + "字段", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!isIDPrime(student.getStudentID())){
                    JOptionPane.showMessageDialog(null, "ID 已经存在！", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                STUDENTS.add(student);
                student = new Student();
                refreshFrame();
            }

            private Boolean isIDPrime(String id){
                for (Student val:STUDENTS){
                    if (val.getStudentID().equals(id)){return false;}
                }
                return true;
            }
            public String allFilled() {
                if (student.getGender() == null || student.getGender().replace(" ","").length() == 0) {
                    return "性别";
                } else if (student.getSchool() == null || student.getSchool().replace(" ","").length() == 0) {
                    return "学校";
                } else if (student.getName() == null || student.getName().replace(" ","").length() == 0) {
                    return "姓名";
                } else if (student.getStudentID() == null || student.getStudentID().replace(" ","").length() == 0) {
                    return "学号";
                } else {
                    return "";
                }
            }
        });

        otherTextField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                student.setOthers(otherTextField.getText());
            }
        });


        p.add(button, constraints);
        return p;
    }

    public JScrollPane bottomPanel() {
        JScrollPane p = new JScrollPane();
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);      //上下分割
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setLayout(new GridLayout(1, 8, 0, 0));
        topPanel.add(new Button("编号"));
        topPanel.add(new Button("姓名"));
        topPanel.add(new Button("年龄"));
        topPanel.add(new Button("学号"));
        topPanel.add(new Button("性别"));
        topPanel.add(new Button("学校"));
        topPanel.add(new Button("班级"));
        topPanel.add(new Button("备注"));

        bottomPanel.setLayout(new GridLayout(STUDENTS.size() < 14?14:STUDENTS.size(),1,0,0));
        int i = 1;
        for (Student val : STUDENTS) {
            bottomPanel.add(getStudentInFoPanel(val,i++));
        }


        splitPane.setLeftComponent(topPanel);
        splitPane.getLeftComponent().setMaximumSize(new Dimension(10, 10));
        splitPane.setRightComponent(bottomPanel);
        p.getViewport().add(splitPane);
        p.setBackground(Color.red);
        return p;
    }

    private JPanel getStudentInFoPanel(Student student,int num) {
        //System.out.println(student.toString());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 8, 0, 0));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(new JTextField(num + "/" + STUDENTS.size()));
        panel.add(new JTextField(student.getName()));
        panel.add(new JTextField(String.valueOf(student.getAge())));
        panel.add(new JTextField(student.getStudentID()));
        panel.add(new JTextField(student.getGender()));
        panel.add(new JTextField(student.getSchool()));
        panel.add(new JTextField(student.getStudentClass()));
        panel.add(new JTextField(student.getOthers()));


        return panel;
    }

    private void refreshFrame() {
        frame.getContentPane().removeAll();
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 1, 1, 1));    //为Frame窗口设置布局为BorderLayout
        // frame.setJMenuBar(menuBar());
        frame.add(topPanel());
        frame.add(bottomPanel());
        frame.setVisible(true);
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        JMenuItem item = new JMenuItem("New(N)", KeyEvent.VK_N);//使用指定的文本和键盘助记符创建 JMenuItem 。
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        item.setName("createNew");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        item = new JMenuItem("Open(O)", KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item.setName("openFile");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        item = new JMenuItem("Save(S)", KeyEvent.VK_S);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item.setName("saveFile");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        menu.addSeparator();
        item = new JMenuItem("Exit(E)", KeyEvent.VK_E);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        item.setName("exit");
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        return menu;
    }

    class fileMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "New(N)":
                    funcNew();
                    break;
                case "Open(O)":
                    funcOpen();
                    break;
                case "Save(S)":
                    funcSave();
                    break;
                case "Exit(E)":
                    funcExit();
            }
        }

        private void funcExit() {
            int n = JOptionPane.showConfirmDialog(null,"确认退出？","确认",
                    JOptionPane.YES_NO_OPTION );
            if (n == JOptionPane.YES_OPTION){
                exit(0);
            }
        }

        private void funcSave() {
            if (FILEPATH.length() == 0){
                FILEPATH = chooseFile();
            }
            if (FILEPATH.length() == 0){
                return;
            }
            //list2XML(FILEPATH);
            File file = new File(FILEPATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "文件创建失败！", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            String writeString = list2XML(STUDENTS);
            if ( file.canWrite()){
                OutputStream stream = null;
                try {
                    stream = new FileOutputStream(file);
                    stream.write(writeString.getBytes("UTF8"));
                    JOptionPane.showMessageDialog(null,"写入成功！","SUCCESS",JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"写入失败！","ERROR",JOptionPane.ERROR_MESSAGE);
                }finally {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            FILEPATH = "";
        }




        private String chooseFile() {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xml", "*.txt");
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(filter);                   //设置一个文件筛选器
            fc.setMultiSelectionEnabled(false);         //设置不允许多选
            /*使用showOpenDialog()方法，显示出打开选择文件的窗口，当选择了某个文件后，或者关闭此窗口那么都会返回一个
            整型数值，如果返回的是0，代表已经选择了某个文件。如果返回1代表选择了取消按钮或者直接关闭了窗口*/
            int result = fc.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                //获取当前选择的文件路径
                File file = fc.getSelectedFile();
                //返回文件路径
                return file.getPath();
            }
            JOptionPane.showMessageDialog(null,"取消！","失败",JOptionPane.ERROR_MESSAGE);
            return "";
        }

        private void funcOpen() {
            FILEPATH = chooseFile();
            if (FILEPATH.length() == 0){
            }
            File file = new File(FILEPATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "文件不存在！", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            try {
                STUDENTS = new ArrayList<Student>();
                StringBuffer XMLBuffer = new StringBuffer();
                Scanner input = new Scanner(file);
                while (input.hasNextLine()){
                    XMLBuffer.append(input.nextLine());
                }
                System.out.println("***********************************\n"
                        +"FILE VALUE:\n"
                        +XMLBuffer.toString() +
                        "************************");
                input.close();
                STUDENTS = XML2list(XMLBuffer.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            refreshFrame();
        }

        private void funcNew() {
        }
    }

    private JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        JMenuItem item = new JMenuItem("暂无功能，就是想加上", KeyEvent.VK_N);//使用指定的文本和键盘助记符创建 JMenuItem 。
        item.addActionListener(new fileMenuListener());
        menu.add(item);
        return menu;
    }

    public static List<Student> getSTUDENTS(){
        return STUDENTS;
    }
    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        Start s = new Start();
    }
}
