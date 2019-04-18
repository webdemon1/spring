package com.alibaba.demon.builder;

/**
 * @author: Demon
 * @create: 2019-04-18
 **/
public class Person {

    private String name;

    private String gender;

    private Integer age;

    private Integer height;

    private Double salary;

    Person(Builder builder) {
        this.name = builder.name;
        this.gender = builder.gender;
        this.age = builder.age;
        this.height = builder.height;
        this.salary = builder.salary;
    }

    public static final class Builder {

        private String name;

        private String gender;

        private Integer age;

        private Integer height;

        private Double salary;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder height(Integer height) {
            this.height = height;
            return this;
        }

        public Builder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", salary=" + salary +
                '}';
    }
}
