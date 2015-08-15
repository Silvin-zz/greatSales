package com.example.pikino.greatsales;

/**
 * Created by silviobravocado on 14/08/15.
 */
public class Parameter {
    private Long id;
    private String name;
    private String parametervalue;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParametervalue() {
        return parametervalue;
    }

    public void setParametervalue(String parametervalue) {
        this.parametervalue = parametervalue;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parametervalue='" + parametervalue + '\'' +
                '}';
    }
}
