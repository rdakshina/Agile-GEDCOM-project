package agile;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class reader {

	public static void main(String[] args) throws IOException {
		 List<Individual> individuals_list = new ArrayList<Individual>();
		List<Family> families_list = new ArrayList<Family>();
		String gedFile = Paths.get(".\\src\\agile\\Prema Gopu.ged").toString();
		parser p = new parser(); 
		p.readFile(gedFile);
		BirthdaysAnniversaries ba = new BirthdaysAnniversaries();
		ba.upcomingBirthdays(p);
		ba.upcomingAnniversaries(p);
		p.listDeceased();
		p.listLivingMarried();
		p.listLivingSingle();
		ValidateDateBeforeCurrentDate db = new ValidateDateBeforeCurrentDate();
		db.validateDatesBeforeCurrentIndv(p);
		db.validateDatesBeforeCurrentFam(p);
		p.genderRoleValidate();
		p.compareb();
		families_list=p.marriagebeforedeath();
		individuals_list=p.divorcebeforedeath();
		AgeLessThan150 obj1= new AgeLessThan150();
		obj1.ageCheck(individuals_list);
		BirthBeforeMarriageOfParents obj2=new BirthBeforeMarriageOfParents();
		obj2.checkChildBirth(individuals_list, families_list);
		MarriageBeforeDivorce obj3=new MarriageBeforeDivorce();
		obj3.compare3(families_list);
		BirthBeforeDeathofParents obj4=new BirthBeforeDeathofParents();
		obj4.compare4(individuals_list, families_list);
		ValidateMarriageAfter14 ma = new ValidateMarriageAfter14();
		ma.validateMarriage(p);
		ValidateUniqueNameBirthdate uq = new ValidateUniqueNameBirthdate();
		uq.validateUniqueNameBirthDate(p);
		p.legitimateDate();
		SiblingSpacing obj5= new SiblingSpacing();
		obj5.CheckBirths(individuals_list, families_list);
		MultipleBirths obj6=new MultipleBirths();
		obj6.CheckBirths(individuals_list, families_list);
	}

}
