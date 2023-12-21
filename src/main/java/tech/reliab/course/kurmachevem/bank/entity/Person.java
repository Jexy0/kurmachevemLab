package tech.reliab.course.kurmachevem.bank.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Person {
    protected UUID id;
    protected String name;
    protected LocalDate birthDate;

    public Person() {
        initWithDefaults();
    }

    public Person(Person person) {
        this.id = UUID.fromString(person.id.toString());
        this.name = person.name;
        this.birthDate = person.birthDate;
    }

    public Person(String name, LocalDate birthDate) {
        initWithDefaults();
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person(UUID id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthdDate='" + getBirthDate() + "'" +
                "\n}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private void initWithDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        birthDate = null;
    }

}