package feedback;

public class Display {


    
    int id;
    String name;
    String mobile;
    String problem;
    String date;

    public Display(int id, String name, String mobile, String problem, String date) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.problem = problem;
        this.date = date;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
