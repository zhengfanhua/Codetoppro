package zuche;

public class CarInfo implements Cloneable {

    private int carNo;//汽车编号
    private String carName;//汽车名称
    private String info;//备注
    private int carBrand;//汽车品牌 1.标致 2.大众 3.奥迪 4.奔驰 5.宝马
    private int carType;//类型 1.紧凑型 2.舒适性 3.SUV 4.精英型
    private double price;//价格
    private int carStatus;//状态 是否可租 1:可以租 2:不可以租
    private String borrowtime;
    private boolean ishuan; //是否还了


    public CarInfo() {
        this.ishuan=false;
    }


    public boolean isIshuan() {
        return ishuan;
    }

    public void setIshuan(boolean ishuan) {
        this.ishuan = ishuan;
    }

    public CarInfo(int carNo, String carName, String info, int carBrand, int carType, double price, int carStatus) {
        this.carNo = carNo;
        this.carName = carName;
        this.info = info;
        this.carBrand = carBrand;
        this.carType = carType;
        this.price = price;
        this.carStatus = carStatus;
        this.ishuan=false;
    }
    public CarInfo(int carNo, String carName, String info, int carBrand, int carType, double price, int carStatus,String borrowtime) {
        this.carNo = carNo;
        this.carName = carName;
        this.info = info;
        this.carBrand = carBrand;
        this.carType = carType;
        this.price = price;
        this.carStatus = carStatus;
        this.borrowtime=borrowtime;
        this.ishuan=false;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public int getCarNo() {
        return carNo;
    }

    public void setCarNo(int carNo) {
        this.carNo = carNo;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(int carBrand) {
        this.carBrand = carBrand;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(int carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    protected CarInfo clone() throws CloneNotSupportedException {
        return (CarInfo) super.clone();
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carNo=" + carNo +
                ", carName='" + carName + '\'' +
                ", info='" + info + '\'' +
                ", carBrand=" + carBrand +
                ", carType=" + carType +
                ", price=" + price +
                ", carStatus=" + carStatus +
                '}';
    }
}