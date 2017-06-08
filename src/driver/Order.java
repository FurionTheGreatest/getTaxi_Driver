package driver;


public class Order {//клас де описані всі можливі конструктори, геттери(беруть значення), сеттери(ставлять значення)
    private int id;
    private String name;
    private String number;
    private String street;
    private String housenumber;
    private String podezd;
    private String taxi_class;
    private String comment;

    public Order(int id, String name, String number, String street, String housenumber, String podezd, String taxi_class, String comment) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.street = street;
        this.housenumber = housenumber;
        this.podezd = podezd;
        this.taxi_class = taxi_class;
        this.comment = comment;
    }
    public Order(int id, String name, String number, String street, String housenumber, String podezd, String comment) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.street = street;
        this.housenumber = housenumber;
        this.podezd = podezd;
        this.comment = comment;
    }




    public Order(int id, String number, String street, String housenumber, String taxi_class, String comment) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.housenumber = housenumber;
        this.taxi_class = taxi_class;
        this.comment = comment;
    }

    public Order(int id, String number, String street, String housenumber, String taxi_class) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.housenumber = housenumber;
        this.taxi_class = taxi_class;
    }

    public Order(String number, String comment) {
        this.number = number;
        this.comment = comment;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getPodezd() {
        return podezd;
    }

    public void setPodezd(String podezd) {
        this.podezd = podezd;
    }

    public String getTaxi_class() {
        return taxi_class;
    }

    public void setTaxi_class(String taxi_class) {
        this.taxi_class = taxi_class;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
