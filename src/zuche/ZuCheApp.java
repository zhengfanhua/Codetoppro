package zuche;

import java.util.*;
import java.text.SimpleDateFormat;


public class ZuCheApp {


    static class Getdate {
        private String strTime = "";

        public Getdate() {
            SimpleDateFormat sj = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            Date date = new Date();
            this.strTime =  sj.format(date);
        }

        @Override
        public String toString() {
            return this.strTime;
        }
    }

    int count = 0;
    //定义用户列表
    List<User> userList = new ArrayList();
    List<CarInfo> carInfolist = new ArrayList<>();

    //借车map
    Map<String, ArrayList<CarInfo>> borrowmap = new HashMap<String, ArrayList<CarInfo>>();


    int count_car = 1;

    //车辆品牌映射
    Map<Integer, String> carBrandMap = new HashMap<>();
    //汽车类型映射
    Map<Integer, String> carTypeMap = new HashMap<>();
    Map<Integer, String> carStatusMap = new HashMap<>();
    Map<Integer, String> carNoMap = new HashMap<>();


    public void start() throws CloneNotSupportedException {
        User admin = new User("admin", "admin", 0);
        userList.add(admin);

        carBrandMap.put(1, "标致 (1)");
        carBrandMap.put(2, "大众 (2)");
        carBrandMap.put(3, "奥迪 (3)");
        carBrandMap.put(4, "奔驰 (4)");
        carBrandMap.put(5, "宝马 (5)");


        carTypeMap.put(1, "紧凑型 (1)");
        carTypeMap.put(2, "舒适型 (2)");
        carTypeMap.put(3, "SUV   (3)");
        carTypeMap.put(4, "精英型 (4)");

        carStatusMap.put(1, "是");
        carStatusMap.put(2, "否");

        //用户可以看到的车辆信息
        CarInfo carInfo1 =
                new CarInfo(count_car++, "朗逸", "自动1.6L", 2, 2, 72, 1);
        CarInfo carInfo2 =
                new CarInfo(count_car++, "途观", "自动1.8T", 2, 3, 200, 1);
        CarInfo carInfo3 =
                new CarInfo(count_car++, "A4L", "自动2.0T", 3, 4, 359, 1);
        CarInfo carInfo4 =
                new CarInfo(count_car++, "308", "手动1.6L", 1, 1, 56, 1);
        CarInfo carInfo5 =
                new CarInfo(count_car++, "308S", "自动1.2T", 1, 2, 70, 1);
        CarInfo carInfo6 =
                new CarInfo(count_car++, "高尔夫", "自动1.4T", 2, 1, 69, 1);
        CarInfo carInfo7 =
                new CarInfo(count_car++, "320Li", "自动2.0T", 5, 4, 500, 1);
        CarInfo carInfo8 =
                new CarInfo(count_car++, "B200", "自动1.6T", 4, 4, 300, 1);
        carInfolist.add(carInfo1);
        carInfolist.add(carInfo2);
        carInfolist.add(carInfo3);
        carInfolist.add(carInfo4);
        carInfolist.add(carInfo5);
        carInfolist.add(carInfo6);
        carInfolist.add(carInfo7);
        carInfolist.add(carInfo8);


        //主程序
        boolean flag = true;//循环控制开关
        while (flag) {
            System.out.println("====================");
            System.out.println("欢迎访问二嗨租车!");
            System.out.println("====================");
            System.out.println("1.登陆  2.注册  3.退出");


            //获取用户在控制台输入的功能
            Scanner scanner = new Scanner(System.in);//控制台输入流
            String input = scanner.nextLine();
            //将字符串转换成整型，进行软件的 匹配
            //转换过程可能会出错（使用try---catch语句）
            int num;
            try {
                /*
                    try-catch语句  用于尝试运行出现异常的代码，需要被尝试运行的语句
                    语句放在try中  错误类型放在catch中
                    try  后面可以跟很多catch
                 */
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                //捕获括号里面写的错误类型
                System.out.println("您输入错误啦！请重新输入！");
                continue;
            }
            switch (num) {
                case 1:
                    //登陆页面
                    login(scanner);
                    break;

                case 2:
                    //注册页面
                    register(scanner);
                    break;
                case 3:
                    //退出
                    flag = false;
                    System.out.println("感谢使用，祝您生活愉快！");
            }
        }

    }


    //登陆界面
    public void login(Scanner scanner) throws CloneNotSupportedException {
        while (true) {
            System.out.println("=======登陆=======>>>>>");
            System.out.println("请输入您的用户名：");
            String userName = scanner.nextLine();
            System.out.println("请输入您密码：");
            String password = scanner.nextLine();
            if (checkUserWithPassword(userName, password)) {
                System.out.println("登陆成功！欢迎：" + userName + "回家！");
                //验证成功，检查是否是管理员
                if ("admin".equals(userName)) {
                    //跳转管理员页面
                    getNormalGuanli(scanner);

                } else {
                    //跳转普通页面
                    getNormalCarInfo(scanner, userName);
                }

                break;//跳出登陆循环
            } else {
                //验证失败
                System.out.println("用户名不存在或密码错误！");
                continue;
            }
        }

    }


    // 登录校验
    public boolean checkUserWithPassword(String userName, String password) {
        boolean isOk = false;
        for (User user : userList) {
            if (userName.equals(user.getUserName())) {
                //用户输入的用户名通过
                if (password.equals(user.getPassword())) {
                    isOk = true;
                }
            }
        }
        return isOk;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        new ZuCheApp().start();
    }


    //注册方法
    public void register(Scanner scanner) {
        while (true) {
            System.out.println("=======注册=======>>>>>");
            System.out.println("请输入您的用户名：");
            String userName = scanner.nextLine();
            //接下来就是检测用户输入的用户名是否被占用
            if (checkUser(userName)) {
                //用户名创建通过
                System.out.println("请输入您的密码：");
                String password = scanner.nextLine();
                //创建用户对象   添加保存到列
                User user = new User(userName, password, ++count);
                userList.add(user);//将对象存放在列表中
                System.out.println("注册成功！可以前往登陆啦！");
                break;
            } else {
                System.out.println("该用户名已被占用，请重新输入！");
                continue;
            }
        }
    }

    //判断方法   判断用户名是否被占用 然后进行用户注册
    public boolean checkUser(String userName) {
        //将用户列表中所有用户名拿出来和新注册的用户名比对     遍历容器
        boolean isOk = true;
        for (User user : userList) {
            if (userName.equals(user.getUserName())) {
                //用户输入的用户名与列表中用户名的内容进行比对
                isOk = false;
            }
        }
        return isOk;
    }


    //显示普通用户页面方法
    public void getNormalCarInfo(Scanner scanner, String username) throws CloneNotSupportedException {
        //遍历输出车辆信息 车辆信息列表里进行遍历
        getAllCarInfo();//调用所有车辆信息
        boolean flag = true;
        while (flag) {
            System.out.println("输入0退出");
            System.out.println("输入1+汽车编号，进入租车订单 如1+2");
            System.out.println("输入2+1 按照价格降序排序 2+2按价格升序");
            System.out.println("输入3+类型编号 按类型搜索");
            System.out.println("输入4+品牌编号 按品牌搜索");
            System.out.println("输入5 查询所有汽车");
            System.out.println("输入6 查询我的租车记录");
            System.out.println("输入7+汽车编号 还车");
            //控制获取用户输入的命令项
            String input = scanner.nextLine();
            String[] arr = input.split("\\+");
            switch (arr[0]) {
                case "0":
                    flag = false;
                    break;

                //进入租赁页面
                case "1":
                    System.out.println("============================================================");
                    //是否具备租车条件
                    boolean isborrow = carInfolist.stream().anyMatch((item) -> Integer.parseInt(arr[1]) == item.getCarNo() && item.getCarStatus() == 1);
                    if (isborrow) {
                        System.out.println("借车成功！租车信息如下：");
                        System.out.println("编号\t汽车名称\t 每日租金  \t   备注 \t 品牌  \t\t类型 \t\t借车时间\t");
                        Iterator<CarInfo> iterator = carInfolist.iterator();
                        while (iterator.hasNext()) {
                            CarInfo carInfo = iterator.next();
                            if (Integer.parseInt(arr[1]) == carInfo.getCarNo()) {
                                //如果该用户存过车
                                if (borrowmap.containsKey(username)) {
                                    ArrayList<CarInfo> carInfos = borrowmap.get(username);
                                    CarInfo clone = carInfo.clone();
                                    clone.setBorrowtime(new Getdate().toString());
                                    carInfos.add(clone);
                                } else {
                                    ArrayList<CarInfo> carInfos = new ArrayList<>();
                                    CarInfo clone = carInfo.clone();
                                    clone.setBorrowtime(new Getdate().toString());
                                    carInfos.add(clone);
                                    borrowmap.put(username, carInfos);
                                }
                                //状态改变
                                carInfo.setCarStatus(2);
                                System.out.println(carInfo.getCarNo() + "\t"
                                        + carInfo.getCarName() + " \t"
                                        + carInfo.getPrice() + "/天 \t"
                                        + carInfo.getInfo() + "\t"
                                        + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                                        + carTypeMap.get(carInfo.getCarType()) + " \t"
                                        +
                                        new Getdate() + "\t");
                                break;

                            }
                        }

                    } else {
                        System.out.println("借车失败！无该编号的车辆或者该车已租");
                    }
                    break;
                case "2":
                    //排序 升序还是降序由于第二个元素决定
                    if ("1".equals(arr[1])) {
                            /*
                               集合操作--排序
                               集合工具类 Collecttions 提供了一个方法sort进行排序
                               sort(待排序的列表吗，比较器)
                               比较器：告诉电脑列表中元素的排序规则 Comparator
                               Comparator 他是 一个接口 ，里面包含了一个带实现的比较方法
                               compare,这个方法返回值 是一个int
                               当这个int>0 表示前一个比后一个大
                               当这个int<0 表示前一个比后一个小
                             */
                        Collections.sort(carInfolist, new Comparator<CarInfo>() {
                            @Override
                            public int compare(CarInfo o1, CarInfo o2) {
                                return (int) (o2.getPrice() - o1.getPrice());
                                //int强制转换数值类型
                            }

                        });
                        getAllCarInfo();//调用所有车辆信息
                        break;

                    } else if ("2".equals(arr[1])) {
                        Collections.sort(carInfolist, new Comparator<CarInfo>() {
                            @Override
                            public int compare(CarInfo o1, CarInfo o2) {
                                return (int) (o1.getPrice() - o2.getPrice());
                                //int强制转换数值类型
                            }

                        });
                        getAllCarInfo();//调用所有车辆信息
                        break;
                    } else {
                        System.out.println("不支持该命令！！");
                        break;
                    }


                case "3":
                    //按照车辆类型 查询
                    System.out.println(carTypeMap.get(Integer.parseInt(arr[1])) + "的车辆有============>>>>>>");
                    System.out.println("编号\t汽车名称\t  备注  \t 品牌  \t\t类型  \t\t 价格 \t 是否可租");
                    for (CarInfo carInfo : carInfolist) {
                        if (Integer.parseInt(arr[1]) == carInfo.getCarType()) {
                            //字符串数组中 第二个元素
                            System.out.println(carInfo.getCarNo() + "\t"
                                    + carInfo.getCarName() + " \t"
                                    + carInfo.getInfo() + "\t"
                                    + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                                    + carTypeMap.get(carInfo.getCarType()) + " \t"
                                    + carInfo.getPrice() + "/天 \t"
                                    + carStatusMap.get(carInfo.getCarStatus()));
                        }

                    }

                    break;
                case "4":
                    //按照车辆品牌类型 查询
                    System.out.println(carTypeMap.get(Integer.parseInt(arr[1])) + "的车辆有============>>>>>>");
                    System.out.println("编号\t汽车名称\t  备注  \t 品牌  \t\t类型  \t\t 价格 \t 是否可租");
                    for (CarInfo carInfo : carInfolist) {
                        if (Integer.parseInt(arr[1]) == carInfo.getCarBrand()) {
                            System.out.println(carInfo.getCarNo() + "\t"
                                    + carInfo.getCarName() + " \t"
                                    + carInfo.getInfo() + "\t"
                                    + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                                    + carTypeMap.get(carInfo.getCarType()) + " \t"
                                    + carInfo.getPrice() + "/天 \t"
                                    + carStatusMap.get(carInfo.getCarStatus()));

                        }
                    }
                    break;

                case "5":
                    getAllCarInfo();//调用所有车辆信息
                    break;
                case "6":
                    System.out.println(username + "的租车记录如下");
                    if (!borrowmap.containsKey(username)) {
                        System.out.println("无租车记录");
                    } else {
                        ArrayList<CarInfo> carInfos = borrowmap.get(username);
                        System.out.println("编号\t汽车名称\t  备注  \t 品牌  \t\t类型  \t\t 价格 \t 借车时间\t是否已还");
                        for (CarInfo carInfo : carInfos) {


                                System.out.println(carInfo.getCarNo() + "\t"
                                        + carInfo.getCarName() + " \t"
                                        + carInfo.getInfo() + "\t"
                                        + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                                        + carTypeMap.get(carInfo.getCarType()) + " \t"
                                        + carInfo.getPrice() + "/天 \t"
                                        + carInfo.getBorrowtime()+"\t"+
                                        (carInfo.isIshuan()?"是":"否"));


                        }
                    }
                    break;
                case "7":
                    //是否需要还
                    boolean needborrow = borrowmap.containsKey(username) && borrowmap.get(username).stream().anyMatch((item) -> item.getCarNo() == Integer.parseInt(arr[1])&&!item.isIshuan());
                    if (!needborrow) {
                        System.out.println("没有需要还的车或者借车编号输入错误");
                    } else {
                        //还车操作
                        //将车状态改成可租状态
                        for (CarInfo carInfo : carInfolist) {
                            if (Integer.parseInt(arr[1]) == carInfo.getCarNo()) {
                                carInfo.setCarStatus(1);
                            }
                        }
                        //将用户的租车列表删除该租车信息
                        Iterator<CarInfo> iterator = borrowmap.get(username).iterator();
                        while (iterator.hasNext()) {
                            CarInfo item = iterator.next();
                            if (item.getCarNo() == Integer.parseInt(arr[1])) {
                                item.setIshuan(true);
                                break;
                            }
                        }
                        System.out.println("还车成功");
                    }


            }


        }
    }

    //打印所有车辆信息  因为多次使用  写成公共方法 避免重复
    public void getAllCarInfo() {
        System.out.println("==========================================");
        System.out.println("编号\t汽车名称\t  备注  \t 品牌  \t\t类型  \t\t 价格 \t 是否可租");
        for (CarInfo carInfo : carInfolist) {
            System.out.println(carInfo.getCarNo() + "\t"
                    + carInfo.getCarName() + " \t"
                    + carInfo.getInfo() + "\t"
                    + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                    + carTypeMap.get(carInfo.getCarType()) + " \t"
                    + carInfo.getPrice() + "/天 \t"
                    + carStatusMap.get(carInfo.getCarStatus()));
        }

    }


    //管理员操作页面
    public void getNormalGuanli(Scanner scanner) {
        //遍历输出车辆信息 车辆信息列表里进行遍历
        getAllCarInfo();//调用所有车辆信息
        boolean flag = true;
        while (flag) {
            System.out.println("输入0退出");
            System.out.println("输入2+1 按照价格降序排序 2+2按价格升序");
            System.out.println("输入3+类型编号 按类型搜索");
            System.out.println("输入4+品牌编号 按品牌搜索");
            System.out.println("输入5 查询所有汽车");
            System.out.println("输入6 增加汽车");
            System.out.println("输入7 修改汽车");
            System.out.println("输入8 查看所有租借信息");
            //控制获取用户输入的命令项
            String input = scanner.nextLine();
            String[] arr = input.split("\\+");
            switch (arr[0]) {
                case "0":
                    flag = false;
                    break;
                case "2":
                    //排序 升序还是降序由于第二个元素决定
                    if ("1".equals(arr[1])) {
                            /*
                               集合操作--排序
                               集合工具类 Collecttions 提供了一个方法sort进行排序
                               sort(待排序的列表吗，比较器)
                               比较器：告诉电脑列表中元素的排序规则 Comparator
                               Comparator 他是 一个接口 ，里面包含了一个带实现的比较方法
                               compare,这个方法返回值 是一个int
                               当这个int>0 表示前一个比后一个大
                               当这个int<0 表示前一个比后一个小
                             */
                        Collections.sort(carInfolist, new Comparator<CarInfo>() {
                            @Override
                            public int compare(CarInfo o1, CarInfo o2) {
                                return (int) (o2.getPrice() - o1.getPrice());
                                //int强制转换数值类型
                            }

                        });
                        getAllCarInfo();//调用所有车辆信息
                        break;

                    } else if ("2".equals(arr[1])) {
                        Collections.sort(carInfolist, new Comparator<CarInfo>() {
                            @Override
                            public int compare(CarInfo o1, CarInfo o2) {
                                return (int) (o1.getPrice() - o2.getPrice());
                                //int强制转换数值类型
                            }

                        });
                        getAllCarInfo();//调用所有车辆信息
                        break;
                    } else {
                        System.out.println("不支持该命令！！");
                        break;
                    }


                case "3":
                    //按照车辆类型 查询
                    System.out.println(carTypeMap.get(Integer.parseInt(arr[1])) + "的车辆有============>>>>>>");
                    System.out.println("编号\t汽车名称\t  备注  \t 品牌  \t\t类型  \t\t 价格 \t 是否可租");
                    for (CarInfo carInfo : carInfolist) {
                        if (Integer.parseInt(arr[1]) == carInfo.getCarType()) {
                            //字符串数组中 第二个元素
                            System.out.println(carInfo.getCarNo() + "\t"
                                    + carInfo.getCarName() + " \t"
                                    + carInfo.getInfo() + "\t"
                                    + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                                    + carTypeMap.get(carInfo.getCarType()) + " \t"
                                    + carInfo.getPrice() + "/天 \t"
                                    + carStatusMap.get(carInfo.getCarStatus()));
                        }

                    }

                    break;
                case "4":
                    //按照车辆品牌类型 查询
                    System.out.println(carTypeMap.get(Integer.parseInt(arr[1])) + "的车辆有============>>>>>>");
                    System.out.println("编号\t汽车名称\t  备注  \t 品牌  \t\t类型  \t\t 价格 \t 是否可租");
                    for (CarInfo carInfo : carInfolist) {
                        if (Integer.parseInt(arr[1]) == carInfo.getCarBrand()) {
                            System.out.println(carInfo.getCarNo() + "\t"
                                    + carInfo.getCarName() + " \t"
                                    + carInfo.getInfo() + "\t"
                                    + carBrandMap.get(carInfo.getCarBrand()) + "\t  "
                                    + carTypeMap.get(carInfo.getCarType()) + " \t"
                                    + carInfo.getPrice() + "/天 \t"
                                    + carStatusMap.get(carInfo.getCarStatus()));

                        }
                    }
                    break;

                case "5":
                    getAllCarInfo();//调用所有车辆信息
                    break;
                case "6":
                    boolean isover=true;
                    while (isover){
                        try {
                            System.out.println("请输入汽车编号");
                            int carNo = Integer.parseInt(scanner.nextLine());
                            System.out.println("请输入汽车名称");
                            String carName = scanner.nextLine();
                            System.out.println("请输入备注");
                            String info = scanner.nextLine();
                            System.out.println("请输入汽车品牌, 1.标致 2.大众 3.奥迪 4.奔驰 5.宝马");
                            int carBrand = scanner.nextInt();
                            System.out.println("请输入类型 1.紧凑型 2.舒适性 3.SUV 4.精英型");
                            int carType = scanner.nextInt();
                            System.out.println("请输入价格");
                            double price = scanner.nextDouble();
                            System.out.println("请输入是否可租，1:可以租 2:不可以租");
                            int carStatus = scanner.nextInt();
                            //校验数据
                            boolean has = carInfolist.stream().anyMatch((item) -> item.getCarNo() == carNo);
                            if (has){
                                System.out.println("编号不能相同，请重新输入");
                                continue;
                            }
                            if (carBrandMap.containsKey(carBrand)&& carTypeMap.containsKey(carType)&& carStatusMap.containsKey(carStatus)){
                                carInfolist.add(new CarInfo(carNo,carName,info,carBrand,carType,price,carStatus));
                                isover=false;
                            }else {
                                System.out.println("数据输入错误");
                            }
                        }catch (Exception e){
                            System.out.println("输入数据格式错误，请重新输入");
                        }

                    }
                    break;
                case "7":
                    System.out.println("请输入汽车编号");
                    int carNo = scanner.nextInt();
                    CarInfo carInfo=null;
                    for (CarInfo carInfo1:carInfolist){
                        if(carInfo1.getCarNo() == carNo){
                            carInfo=carInfo1;
                        }
                    }

                    if(carInfo==null){
                        System.out.println("汽车编号不存在");
                    }else {
                        System.out.println("请输入要修改的内容，1，汽车名称，2.备注，3.汽车品牌，4.类型，5.价格，6.状态");
                        int option = scanner.nextInt();
                        if(option==1){
                            System.out.println("请输入修改后的汽车名称");
                            String carName = scanner.nextLine();
                            carInfo.setCarName(carName);
                        }else if(option==2){
                            System.out.println("请输入修改后的备注");
                            String info = scanner.nextLine();
                            carInfo.setInfo(info);
                        }else if(option==3){
                            System.out.println("请输入修改后的汽车品牌");
                            int carBrand = scanner.nextInt();
                            carInfo.setCarBrand(carBrand);
                        }else if(option==4){
                            System.out.println("请输入修改后的汽车类型");
                            int carType = scanner.nextInt();
                            carInfo.setCarType(carType);
                        } else if(option==5){
                            System.out.println("请输入修改后的汽车价格");
                            double price = scanner.nextDouble();
                            carInfo.setPrice(price);
                        }else if(option==6){
                            System.out.println("请输入修改后的状态");
                            int carStatus = scanner.nextInt();
                            carInfo.setCarStatus(carStatus);
                        }
                        System.out.println("修改成功");

                    }
                    break;
                case "8":
                    System.out.println("编号\t汽车名称\t 每日租金  \t   备注 \t 品牌  \t\t类型 \t\t借车时间\t\t是否已还");
                    for(List<CarInfo> carInfoList:borrowmap.values()){
                        for(CarInfo carInfo1:carInfoList){
                            System.out.println(carInfo1.getCarNo() + "\t"
                                    + carInfo1.getCarName() + " \t"
                                    + carInfo1.getPrice() + "/天 \t"
                                    + carInfo1.getInfo() + "\t"
                                    + carBrandMap.get(carInfo1.getCarBrand()) + "\t  "
                                    + carTypeMap.get(carInfo1.getCarType()) + " \t"
                                    + carInfo1.getBorrowtime() +"\t"+
                                    (carInfo1.isIshuan()?"是":"否"));
                        }
                    }
                }



            }
        }
    }





