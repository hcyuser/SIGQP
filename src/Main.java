import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		double experiment = 0;
		for(int  e=0;e<1;e++) {

			int totalusers = 100;
			int totalinterest = 50;
			double xrange =  10;
			double yrange =  10;
			ArrayList<User> presentUser = new ArrayList<>();
			for(int i=0;i<totalusers;i++) {
				User instance = new User();
				instance.x = (Math.random()*xrange);
				instance.y = (Math.random()*yrange);
				instance.id = i;
				for(int ii=0;ii< (int)(Math.random()*totalinterest+1);ii++) {

					instance.interest.add((int)(Math.random()*totalinterest+1));
				}
				presentUser.add(instance);
				instance = null;

			}
			//show the result of random and the presentUser
			for(int i=0;i<presentUser.size();i++) {
				//System.out.println(i+" "+presentUser.get(i).x+" "+presentUser.get(i).y+" "+presentUser.get(i).interest.size());


			}
			/*Greedy gr = new Greedy(new ArrayList<>(presentUser));
			gr.countFar();
			gr.findSetCover();*/

			/*heuristicGreedy hgr = new heuristicGreedy(new ArrayList<>(presentUser));
			hgr.countFar();
			hgr.findSetCover();*/
			
			/*GAR gar = new GAR(new ArrayList<>(presentUser));
			gar.RandomChoose();
			gar.countFar();
			gar.findSetCover();*/

			/*RAG rag = new RAG(new ArrayList<>(presentUser));
			rag.countFar();
			rag.findSetCover();
			rag.RandomChoose();*/

			/*Set<User> set = gr.getGoal();
			set.retainAll(rag.getGoal());
			System.out.println((double)set.size()/gr.getGoal().size());
			experiment = experiment+(double)set.size()/gr.getGoal().size();*/
			/*ArrayList<User> presentUser2 = new ArrayList<>();
			User t1 = new User();
			t1.interest.add(1);
			t1.interest.add(2);
			t1.interest.add(3);
			User t2 = new User();
			t2.interest.add(4);
			t2.interest.add(2);
			User t3 = new User();
			t3.interest.add(4);
			t3.interest.add(3);
			User t4 = new User();
			t4.interest.add(4);
			t4.interest.add(5);
			presentUser2.add(t1);
			presentUser2.add(t2);
			presentUser2.add(t3);
			presentUser2.add(t4);

			MSC msc = new MSC(new ArrayList<>(presentUser2));*/
			
			ArrayList<User> presentUser3 = new ArrayList<>();
			User u1 = new User();
			u1.interest.add(1);
			u1.x = 0;
			u1.y = 0;
			User u2 = new User();
			u2.interest.add(2);
			u2.x = 0;
			u2.y = 0;
			User u3 = new User();
			u3.interest.add(2);
			u3.x = 5;
			u3.y = 6;
			presentUser3.add(u1);
			presentUser3.add(u2);
			MSC_distance msc_distance = new MSC_distance(new ArrayList<>(presentUser3));
	
			
			

		}	


		//System.out.println(" final "+experiment/100);



	}

}
