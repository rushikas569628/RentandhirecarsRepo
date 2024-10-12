package com.example.rentandhirecars;


public class Cars {
    private int id;
    private String name,Model,Milleage;
    private String imageURL;
    public Cars(int id, String name, String imageURL,String Model,String Milleage) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.Model = Model;
        this.Milleage = Milleage;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", Model='" + Model + '\'' +
                ", Milleage='" + Milleage + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return Model;
    }
    public String getMilleage() {
        return Milleage;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
