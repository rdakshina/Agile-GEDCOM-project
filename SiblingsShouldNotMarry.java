package agile;
import java.util.HashMap;
public class SiblingsShouldNotMarry {

		public HashMap<String, Object> hm = new HashMap<String, Object>();
		public parser var= new parser();
		
		void compare7(parser p){
			
			for (Individual induvidual : p.individuals_list) {

				hm.put(induvidual.getId(), induvidual);
				hm.put(induvidual.getChild(), induvidual);
				hm.put(induvidual.getSpouse(), induvidual);
			}

			for (Family fam : p.families_list) {
				
				String husband_id=fam.getHusbandId();
				String wife_id=fam.getWifeId();
				//String fam_id="@"+fam.getId()+"@";
				
				Individual h = (Individual) hm.get(husband_id);
				Individual w = (Individual) hm.get(wife_id);
				
				String hChild_of_family=h.getChild();
				String wChild_of_family=w.getChild();
				
				if(hChild_of_family!=null){
				if(hChild_of_family.equals(wChild_of_family)){
					System.out.println("ERROR:FAMILY: US18 :"+wife_id+" is married to sibling "+husband_id+" in family "+fam.getId());
				}
				}
				
			}	
		}
		
	

}
