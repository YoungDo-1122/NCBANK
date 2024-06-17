package ncbank.beans;

import java.sql.Date;

public class GroupAccountBean {

	private int group_num; 
	private int user_num; 
	private Date group_joindate; 
	private String group_leader;
	
	
	
	
	public int getGroup_num() {
        return group_num;
    }
    public void setGroup_num(int group_num) {
        this.group_num = group_num;
    }
    public int getUser_num() {
        return user_num;
    }
    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }
    public Date getGroup_joindate() {
        return group_joindate;
    }
    public void setGroup_joindate(Date group_joindate) {
        this.group_joindate = group_joindate;
    }
    public String getGroup_leader() {
        return group_leader;
    }
    public void setGroup_leader(String group_leader) {
        this.group_leader = group_leader;
    }
	
	

	

}
