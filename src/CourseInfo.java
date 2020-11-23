import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CourseInfo
{

    private String code;
    private String name;
    private String type;

    private ArrayList<ClassInfo> classArrayList;

    // constructor for add course
    public CourseInfo(CourseInfo courseInfo)
    {
        this.code = courseInfo.code;
        this.name = courseInfo.name;
        this.type = courseInfo.type;

        classArrayList = new ArrayList<ClassInfo>();
    }

    // constructor for DB init
    public CourseInfo(String courseInfo)
    {
        String[] courseInfoArr = courseInfo.split(",");
        code = courseInfoArr[0];
        name = courseInfoArr[1];
        type = courseInfoArr[2];

        classArrayList = new ArrayList<ClassInfo>();
    }

    public String getCourseInfo()
    {
        return (code + " " + name + " " + type);
    }

    public String getName() {return name;}

    public String getCode() {return code;}

    public String getType() { return type; }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addClass(ClassInfo classInfo)
    {
        this.classArrayList.add(classInfo);
    }

    public ArrayList<ClassInfo> getClassList()
    {
        return this.classArrayList;
    }

    public void printCourseInfo(){
        System.out.println("======= Display Course Info =======");
        System.out.println("Course code: " + getCode());
        System.out.println("Course name: " + getName());
        System.out.println("Course type: " + getType());
    }

    public void EditCourseInfoOptions(String [] editOptions){
        System.out.println("============");
        for(int k = 0; k < editOptions.length;k++){
            System.out.println((k+1) + ". Edit " +  editOptions[k]);
        }
        System.out.println("============");
        System.out.println("Enter your input: ");
    }

    public void createClassArrayList() {this.classArrayList = new ArrayList<>();}

    public String getAllRecordInDB(String skipByColumnName){
        ArrayList<String> d = new ArrayList<String>();
        for(var i :  Singleton_CourseInfo.getInstance().courseInfoDB){
            if(!(i.getCode().equals(skipByColumnName))){
                d.add(getRecordInDB(i));
            }
        }
        return String.join("\r\n",d);
    }

    public String getRecordInDB(CourseInfo data){
        String [] d = new String[] {data.getCode(),data.getName(),data.getType()};
        return String.join(",",d);
    }

    public boolean checkDuplicateCourse(CourseInfo newCourse){
        boolean output = true;

        var courses = Singleton_CourseInfo.getInstance().courseInfoDB;
        var getCourseInfo = courses.stream().filter(x -> x.getCode().equals(newCourse.getCode())
                && x.getName().equals(newCourse.getName()) && x.getType().equals(newCourse.getType()))
                .collect(Collectors.toList());

        if(getCourseInfo.size() == 0){
            output = false;
        }

        return output;
    }

}