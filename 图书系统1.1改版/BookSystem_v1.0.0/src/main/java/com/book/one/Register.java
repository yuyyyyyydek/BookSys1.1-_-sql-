package com.book.one;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame {

    Font font2 = new Font("Serif", Font.BOLD, 17);//设置文字大小
    //amount方法中的声明项
    private JLabel jLblank;//"账号注册信息中不能有空框"文本域声明
    private JButton bdef;//"账号注册信息中不能有空框"弹出窗的确定按钮

    private String addAccount;//新注册的账号

    private String addPass;//新注册账号的密码

    private String account;    //管理员账号字符串
    private String password;//管理员密码字符串

    //Font font2 = new Font("Serif", Font.BOLD, 28);//设置文字大小

    private JButton blog;//注册按钮 的声明

    private JLabel jLaccount;//请输入您的账号
    private JLabel jLpass;//请输入您的密码

    private JTextField textField;//文本框，信息为输入的注册账号

    private JTextField textField2;// 文本框，信息为输入的注册账号的密码

    void register() {
        MainApp.staticdata.countreg = 1;

        setTitle("注册");//设置窗口标题
        setSize(600, 140);//设置窗口大小
        setLayout(null);//设置窗口布局
        setResizable(false);//设置窗口是否可以改变大小
        this.setLocationRelativeTo(null);//窗口居中显示
        setVisible(true);//设置窗口可见
        jLaccount = new JLabel("注册账号");//实例化账号文本域，并为账号文本域赋值
        jLaccount.setFont(font2);//设置账号文本域的文字的大小格式
        jLaccount.setBounds(20, 10, 80, 40);//账号文本域的位置大小设置
        jLpass = new JLabel("注册密码");//实例化密码文本域,并为密码文本域赋值
        jLpass.setFont(font2);//设置密码文本域的字体大小格式
        jLpass.setBounds(280, 10, 80, 40);//密码文本域的位置大小设置
        add(jLpass);//将“密码”文本域添加到窗口
        add(jLaccount); //将"账号"文本域添加到窗口
        textField = new JTextField();//实例化文本框，该文本框为 输入的账号
        textField.setFont(font2);//为文本框设置字体大小格式
        textField.setBounds(90, 20, 160, 25);//账号文本框的位置大小设置
        textField2 = new JTextField();//实例化密码文本框，该文本框为 输入的密码
        textField2.setFont(font2);//为密码文本框设置文字大小格式
        textField2.setBounds(350, 20, 160, 25);//密码文本框位置大小格式
        add(textField);//将账号文本框添加到窗口
        add(textField2);//将密码文本框添加到窗口
        blog = new JButton("注册账号");//注册账户的按钮  实例化
        blog.setFont(font2);//注册账户的按钮字体大小格式设置
        blog.setBounds(210, 60, 120, 30);
        add(blog);//将注册按钮添加到窗口中
        //add(bre);//将重置按钮添加到注册窗口中
        blog.addActionListener(listener);//给注册账号按钮添加监听事件
        jLaccount.setForeground(Color.RED);
        jLpass.setForeground(Color.RED);

        repaint();//窗口重绘


        //设置窗体关闭执行的代码
        addWindowListener(new WindowAdapter() {//添加窗体监听器

            public void windowClosing(WindowEvent e) {//窗口关闭时执行的代码
                super.windowClosing(e);
                MainApp.staticdata.countreg = 0;  //countreg注册窗口是否存在 ，重置为0，表示不存在
            }

        });


    }//register注册方法  end


    //以下为获取文本框信息的方法
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
            //account= gain(jAdmini);//调用此方法获取管理员账号文本框信息
            System.out.println("您输入的账号为" + account);//输出管理员用户输入的账号
            //password = gain2(jPassword);//获取管理员密码框中的内容信息

            System.out.println("您输入的密码为：" + password);//输出管理员用户输入的密码


            addAccount = gain(textField);//将从新注册账号的账号文本框获取的文本信息赋值到已经声明的注册账号字符串中
            addPass = gain(textField2);//将从新注册账号的密码文本框获取的文本信息赋值到已经声明的注册账号的密码字符串中


            updata3(addAccount, addPass);
            MainApp.staticdata.countreg = 0;//设置此注册窗口不存在，重置为0方便下次创建
            dispose();


        }
    };//listener  END


    //实现获得信息并将信息更新进数据库的方法
    void updata3(String addname2, String addpass2) {//新增数据库表信息，为使用者账号和密码


        // 数据库连接配置
        String url = "jdbc:mysql://localhost:3306/booksystem";
        String user = "root";
        String password = "123456";

        {
            try {
                // 加载数据库驱动
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 建立连接
                Connection con = DriverManager.getConnection(url, user, password);


                //填上管理员密码后的新增账号空框提示
                //注册信息不能为空，测试异常
                if (addname2.equals("") || addpass2.equals("")) {//如果任意项为空则抛出异常，停止后续代码的执行，防止空值进入数据库

                    System.out.println("账号密码不为空");
                    throw new Exception();//如果信息为空抛出异常停止以后的代码执行
                }

//                //图书数量与不为0：
//                if(bookamount < 0||bookprice.compareTo(bokamount)<0){//与声明的数量为0进行比较
//                    throw new RuntimeException();//如果信息为空抛出异常停止以后的代码执行
//                }


                //使用PreparedStatement
                //  准备SQL语句
                String sqladd = "INSERT INTO t_user (username, password) VALUES (?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sqladd);

                // 4. 设置参数值
                preparedStatement.setString(1, addname2);//为第1个问号赋值
                preparedStatement.setString(2, addpass2);//为第2个问号赋值


                int rowsAffected = preparedStatement.executeUpdate();


                if (rowsAffected == 1) {//如果所有新增成功了就执行以下代码，弹出成功窗口
                    System.out.println("注册账号成功");//在控制台输出"注册成功"
                    if (MainApp.staticdata.countregwin == 0) {//检查成功窗口是否为一个
                        win();
                    }
                }


                // 关闭资源
//                rs.close();
                preparedStatement.close();
                con.close();

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();

            } catch (RuntimeException e) {
                //amount();
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }//updata2() end

    //注册成功后弹出的注册成功的消息窗
    void win() {
        MainApp.staticdata.countregwin = 1;//本窗体是否已经存在以及只能存在一个该窗体的判断代码//此处为注册成功信息成功
        JButton win;//声明一个按钮
        JLabel jLabel;//注册成功的提示
        JFrame jwin = new JFrame();//创建一个窗体对象
        jwin.setLayout(null);//设置窗体布局管理器为绝对布局
        jwin.setSize(200, 140);//设置窗体大小
        jwin.setLocationRelativeTo(null);//窗口居中显示
        jwin.setResizable(false);//设置窗口是否可以改变大小
        jLabel = new JLabel("注册成功");//实例化文本域
        win = new JButton("确定");//实例化一个按钮。确认注册成功的按钮
        win.setFont(font2);//设置按钮字体大小样式
        jLabel.setFont(font2);//设置文本域字体大小样式
        jLabel.setBounds(56, 0, 180, 40);//设置成功信息文字的大小和位置
        win.setBounds(42, 44, 100, 30);//设置确定按钮的大小和位置
        jwin.add(win);//添加确认按钮到窗体
        jwin.add(jLabel);//将文本域加入到成功窗体中
        jwin.setVisible(true);//设置弹出的窗体可见
        win.addMouseListener(new MouseListener() {//定义注册成功窗口中按钮的监听事件
            @Override
            public void mouseClicked(MouseEvent e) {//鼠标单击事件
                jwin.dispose();//jwin窗体关闭
                MainApp.staticdata.countregwin = 0;//将验证是否存在此窗口的判断重置为0，方便下次再次弹出成功信息窗口
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //设置窗体关闭执行的代码
        jwin.addWindowListener(new WindowAdapter() {//添加窗体监听器

            public void windowClosing(WindowEvent e) {//窗口关闭时执行的代码
                super.windowClosing(e);
                MainApp.staticdata.countregwin = 0;  //countup重置为0
            }

        });

    }   //win  end
}

