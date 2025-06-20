import java.util.*;

class Person {
    private String name, email, phone, address;

    public Person(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public void display() {
        System.out.println("== Personal Details ==");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }
}

class Education {
    private String degree, institution;
    private int year;
    private double grade;

    public Education(String degree, String institution, int year, double grade) {
        this.degree = degree;
        this.institution = institution;
        this.year = year;
        this.grade = grade;
    }

    public void display() {
        System.out.println("Degree: " + degree + ", Institution: " + institution +
                ", Year: " + year + ", Grade: " + grade);
    }
}

class Experience {
    private String company, role, duration;

    public Experience(String company, String role, String duration) {
        this.company = company;
        this.role = role;
        this.duration = duration;
    }

    public void display() {
        System.out.println("Company: " + company + ", Role: " + role + ", Duration: " + duration);
    }
}

class Skill {
    private String skillName;
    private String proficiency;

    public Skill(String skillName, String proficiency) {
        this.skillName = skillName;
        this.proficiency = proficiency;
    }

    public void display() {
        System.out.println(skillName + " (" + proficiency + ")");
    }
}

class Resume {
    private Person person;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Skill> skillList;

    public Resume(Person person) {
        this.person = person;
        this.educationList = new ArrayList<>();
        this.experienceList = new ArrayList<>();
        this.skillList = new ArrayList<>();
    }

    public void addEducation(Education edu) {
        educationList.add(edu);
    }

    public void addExperience(Experience exp) {
        experienceList.add(exp);
    }

    public void addSkill(Skill skill) {
        skillList.add(skill);
    }

    public void displayResume() {
        person.display();
        System.out.println("\n== Education ==");
        for (Education e : educationList) e.display();

        System.out.println("\n== Work Experience ==");
        for (Experience e : experienceList) e.display();

        System.out.println("\n== Skills ==");
        for (Skill s : skillList) s.display();
    }
}

public class ResumeBuilder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Personal Info
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        Person person = new Person(name, email, phone, address);
        Resume resume = new Resume(person);

        // Education
        int eduCount = 0;
        while (true) {
            System.out.print("How many education entries? ");
            try {
                eduCount = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        for (int i = 0; i < eduCount; i++) {
            System.out.println("Enter Degree, Institution, Year, Grade:");
            String degree = sc.nextLine();
            String institution = sc.nextLine();
            int year = 0;
            while (true) {
                try {
                    year = Integer.parseInt(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid year.");
                }
            }

            double grade = 0;
            while (true) {
                try {
                    grade = Double.parseDouble(sc.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid grade.");
                }
            }

            resume.addEducation(new Education(degree, institution, year, grade));
        }

        // Experience
        int expCount = 0;
        while (true) {
            System.out.print("How many work experiences? ");
            try {
                expCount = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        for (int i = 0; i < expCount; i++) {
            System.out.println("Enter Company, Role, Duration:");
            String company = sc.nextLine();
            String role = sc.nextLine();
            String duration = sc.nextLine();
            resume.addExperience(new Experience(company, role, duration));
        }

        // Skills
        int skillCount = 0;
        while (true) {
            System.out.print("How many skills? ");
            try {
                skillCount = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        for (int i = 0; i < skillCount; i++) {
            System.out.println("Enter Skill Name and Proficiency:");
            String skillName = sc.nextLine();
            String proficiency = sc.nextLine();
            resume.addSkill(new Skill(skillName, proficiency));
        }

        // Display
        System.out.println("\n\n=============================");
        System.out.println("         YOUR RESUME         ");
        System.out.println("=============================");
        resume.displayResume();
        sc.close();
    }
}
