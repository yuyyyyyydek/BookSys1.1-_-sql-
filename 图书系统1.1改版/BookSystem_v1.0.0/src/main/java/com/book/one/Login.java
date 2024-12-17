package com.book.one;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{

    Font font = new Font("Serif",Font.BOLD,17);//设置文字大小

    private JTextField textField;//账号文本框
    private JPasswordField jPassword;//密码文本框
    private JLabel jLabel ;//账号文字
    private JLabel jLabel2 ;//密码文字
    private JButton b1;//按钮1  账号登陆
    private JButton b2;//按钮2  账号注册
    private Register register;//注册窗口声明
    private String account;	//账号字符串
    private String password;//密码字符串



    void initFrame() {
        setTitle("");//设置窗口标题
        setSize(520,160);//设置窗口大小
        setLayout(null);//设置窗口布局
        setResizable(false);//设置窗口是否可以改变大小
        this.setLocationRelativeTo(null);//窗口居中显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体关闭方式
        setVisible(true);//设置窗口可见
        textField = new JTextField();//账号文本框
        jPassword = new JPasswordField();//密码文本框
        textField.setDocument(new TextLimit(15));
        jPassword.setDocument(new TextLimit(15));
        jPassword.setEchoChar('*');//设置密码不可见
        textField.setBounds(70,20,160,25);//账号文本框位置大小设定  -----
        jPassword.setBounds(330,20,160,25);//密码文本框位置大小设定
        jLabel = new JLabel("账号");//显示账号文字
        jLabel2 = new JLabel("密码");//显示密码文字

        jLabel.setFont(font);//应用账号文字大小
        jLabel2.setFont(font);//应用密码文字大小
        jLabel.setBounds(20,10,50,40);//账号文本框位置大小设定
        jLabel2.setBounds(280,10,50,40);//密码文本框位置大小设定
        b1 = new JButton("登陆");//登陆按钮
        b2 = new JButton("注册");//注册按钮
        b1.setSize(120,30);//设置按钮1登陆按钮大小
        b2.setSize(120,30);//设置按钮2注册按钮大小
        b1.setLocation(20,80);//设置按钮1登陆按钮位置
        b2.setLocation(355,80);//设置按钮2注册按钮位置
        b1.setFont(font);//设置按钮1字体大小
        b2.setFont(font);//设置按钮2字体大小
        add(jLabel);//添加账号文字至窗口
        add(jLabel2);//添加密码文字至窗口
        add(textField);//添加账号文本框至窗口
        add(jPassword);//添加密码文本框至窗口
        add(b1);//添加登录按钮到窗口
        add(b2);//添加注册按钮到窗口
        repaint();//重绘画面
        b1.addActionListener(listener);//给按钮1登录按钮添加监听事件
        b2.addActionListener(listener2);//给按钮2注册按钮添加监听事件
        // 设置 JLabel 的文本颜色为红色
        jLabel.setForeground(Color.RED);
        jLabel2.setForeground(Color.RED);


    }//init() end




    String gain(JTextField textField) {//获得账号文本框的内容信息
        String text = textField.getText();//获取账号文本框中的内容信息
        //System.out.println("获取文本框内容");//提示信息
        return text;//返回账号框中的内容
    }

    String gain2(JPasswordField jPassword) {//获取密码文本框中的内容
        char[] text = jPassword.getPassword();//获取密码框字符数集
        String password = String.valueOf(text);//将字符数集转化为字符串
        return password;//返回密码框中的内容
    }

    ActionListener listener = new ActionListener() {//添加事件监听

        @Override
        public void actionPerformed(ActionEvent e) {//重写方法
            account= gain(textField);//调用获取账号文本框信息
            System.out.println("您输入的账号为" + account);//输出用户输入的账号
            password = gain2(jPassword);//获取密码框中的内容信息

            System.out.println("您输入的密码为：" + password);//输出用户输入的密码

            while(jdbc(account,password))  {  //创建的账号密码传送jdbc方法中进行账号密码验证
                new MainApp().main();//创建图书管理系统的主窗体
                setVisible(false);//设置此登录窗口隐藏
                break;//退出验证账号密码信息循环
            }

        }
    };//listener  END

    ActionListener listener2 = new ActionListener() {//添加事件监听

        @Override
        public void actionPerformed(ActionEvent e) {//重写方法
            //register = new Register();//注册窗口创建
            //register.register();//调用注册窗口构造方法，使窗口创建
            if(MainApp.staticdata.countreg == 0) {//如果注册窗口不存在，按下此按钮弹出注册窗口
                new Register().register(); //显示注册窗口
            }
        }
    };//listener  END


    //以下代码会将输入的账号密码信息与数据库中的账号密码信息进行比较，比较成功则完成登陆
    boolean jdbc(String account,String password){//定义jdbc连接数据库方法
        // JDBC驱动程序名称及URL
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/booksystem";
        // 数据库登录信息
        String username = "root";
        String Password = "123456";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try {
            //2.注册驱动

            Class.forName(driver);
            //3.获取数据库连接对象
            conn = DriverManager.getConnection(url,username,Password);


            //5.获取执行sql的对象 Statement,创建Statement对象
            stmt = conn.createStatement();

            //执行sql
            String sql = "SELECT username,password FROM t_user";//sql语句
            rs = stmt.executeQuery(sql);//获取查询语句执行结果


            while (rs.next()) {//查询结果rs遍历，循环输出
                String name = rs.getString("username");//获取从数据库查询到的用户信息至name属性
                //System.out.println(" 遍历结果的Name: " + name);//输出打印该数据库中name的信息
                String password2 =rs.getString("password");//获取从数据库查询到的密码信息至password2属性
                //System.out.println("数据库中的密码为" + password2);//输出打印该数据库中password信息
                if(account.equals("")) {//如果账号框为空执行此if中的内容
                    System.out.println("请输入账号");//输出警告信息
                }else if(password.equals("")) {//如果密码框为空执行此if中的内容
                    System.out.println("请输入密码");//输出警告信息
                }

                while(account.equals(name)) {//循环判断用户输入账号与数据库中查询得到的账号信息是否相同

                    if(password2.equals(password)) {//如果用户输入的密码与数据库中的密码相同进入此if中
                        System.out.println("登录成功");//提示用户登录成功
                        System.out.println("password:" + password2);//输出用户输入的正确密码
                        return true;//返回ture，以便其它循环使用
                    }
                    break;//输入的账号正确，密码不正确，需要从此break语句跳出while循环
                }
//为以后恢复数据保留该条语句	      System.out.println("登录失败，请重新输入");//账号密码为空或输如的账号密码不在数据库中，输出此警告信息

            }
            System.out.println("登录失败，请重新输入");//账号密码为空或输如的账号密码不在数据库中，输出此警告信息

            return false;//信息不正确，返回false，以便其它循环条件的使用

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();//打印异常信息
        }finally {

            try {
                if (stmt!=null){//如果stmt不为空，释放此资源
                    //8.释放资源
                    stmt.close(); //关闭Statement对象
                }
            } catch (SQLException throwables) {//出现异常后执行的代码
                throwables.printStackTrace();//打印异常信息
            }

            try {
                if (conn!=null){//如果conn不为空，释放此资源
                    conn.close();//关闭数据库连接对象
                }
            } catch (SQLException throwables) {//出现异常后执行的代码
                throwables.printStackTrace();//抛出异常信息
            }
        }
        return false;
    }//JDBC  end



}//Enter类 end
