import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;



public class GAR {
	double alpha = 0.5;
	int k=20;
	double ratio = 0.9;
	double far = 0;
	ArrayList<User> greedyPresentUser = new ArrayList<>();
	ArrayList<User> goal = new ArrayList<>();
	ArrayList<Rank> totalrank = new ArrayList<>();
	GAR(ArrayList<User> input){
		greedyPresentUser = input;
	}
	public void RandomChoose() {
		int numofrandom = (int) ((int)k*ratio);
		for(int i=0;i<numofrandom;i++) {
			User chosen  = greedyPresentUser.get((int) (Math.random()*greedyPresentUser.size()));
			goal.add(chosen);
			greedyPresentUser.remove(chosen);
		}

	}
	
	public void countFar() {

		for(int i=0;i<greedyPresentUser.size();i++) {
			for(int y=0;y<greedyPresentUser.size();y++) {
				double distance = Math.pow((Math.pow( (greedyPresentUser.get(i).x-greedyPresentUser.get(y).x), 2)+Math.pow( (greedyPresentUser.get(i).y-greedyPresentUser.get(y).y), 2)),0.5);
				if(distance>=far) {
					far = distance;
				}

			}

		}
		//System.out.println(far);

	}

	public void findSetCover() {
		for(int i=0;i<(int)(k-goal.size());i++) {
			for(int j=0;j< greedyPresentUser.size();j++) {
				for(int k=0;k<greedyPresentUser.size();k++){
					int I = Math.min( greedyPresentUser.get(j).interest.size(), greedyPresentUser.get(k).interest.size());
					double instanceRank = alpha*I + (1-alpha)*(far-Math.pow((Math.pow( (greedyPresentUser.get(j).x-greedyPresentUser.get(k).x), 2)+Math.pow( (greedyPresentUser.get(j).y-greedyPresentUser.get(k).y), 2)),0.5));
					processDuplicateRank(j,k,instanceRank);
				}
			}

			sortRank();

			/*for(Rank a : totalrank) {
				System.out.println(greedyPresentUser.get(a.user1).id+" - "+greedyPresentUser.get(a.user2).id+"::"+a.rank);
			}*/
			//System.out.println("-----");
			
				goal.add(greedyPresentUser.get(totalrank.get(0).user1));
				greedyPresentUser.remove(totalrank.get(0).user1);
				totalrank.clear();
				
			
			
		}
		

	}
	public void sortRank() {

		// Sorting
		Collections.sort(totalrank, new Comparator<Rank>() {
			@Override
			public int compare(Rank a, Rank b) {
				return Double.compare(b.rank, a.rank);
			}
		});
	}
	public void processDuplicateRank(int userf,int users,double ir) {
		if(userf!=users) {
			boolean isExist = false;
			for(Rank a :totalrank) {
				if(a.user1==userf&&a.user2==users) {
					isExist = true;
				}
				if(a.user1==users&&a.user2==userf) {
					isExist = true;
				}
			}
			if(!isExist) {
				Rank instance = new Rank();
				instance.user1 = userf;
				instance.user2 = users;
				instance.rank =  ir ;
				totalrank.add(instance);
				instance = null;

			}

		}
	}
	public Set getGoal() {
		Set<User> set = new HashSet<User>(goal);
		return set;
	}

}
