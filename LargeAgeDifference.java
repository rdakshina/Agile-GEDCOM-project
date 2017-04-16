package agile;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
//import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LargeAgeDifference {
	public HashMap<String, Object> hm = new HashMap<String, Object>();
	public parser var= new parser();
	
	void compare8(List<Individual> individuals_list,List<Family> families_list){
		for (Individual induvidual : individuals_list) {

			hm.put(induvidual.getId(), induvidual);
			hm.put(induvidual.getBirthDate(), induvidual);
		}
		for (Family fam : families_list) {
			String fid=fam.getId();
			String husband_id = fam.getHusbandId();
			String wife_id = fam.getWifeId();
			if (fid != null) {
		    	LocalDate marriageDate=null;
		    	LocalDate husbandBDate=null;
		    	LocalDate wifeBDate=null;
		    String WeddingDate=fam.getWeddingDate();
			
			Individual i = (Individual) hm.get(husband_id);
			String hBdate=i.getBirthDate();
			i = (Individual) hm.get(wife_id);
			String wBdate=i.getBirthDate();
			marriageDate =var.convertToLocalDate(WeddingDate);
			husbandBDate =var.convertToLocalDate(hBdate);
			wifeBDate =var.convertToLocalDate(wBdate);
			
			//indv.setAge(calculateAge(indv.getBirthDate(), LocalDate.now().toString()));
			int hage=Period.between(husbandBDate, marriageDate).getYears();
			int wage=Period.between(wifeBDate, marriageDate).getYears();
			if(hage>(2*wage)){
				System.out
				.println("ERROR: FAMILY: US34: Age of husband "+husband_id+" is more than the age of the wife "+wife_id+" in family "+fid);
			}
			else if(wage>(2*hage)){
				System.out
				.println("ERROR: FAMILY: US34: Age of wife "+wife_id+" is more than the age of the husband "+husband_id+" in family "+fid);
			}
			
		}
			}
				
			}
			
}
