import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.MatchingAlgorithm.*;
import org.jgrapht.graph.*;

public class MSC {
	MSC(ArrayList<User> input){
		Set<User> set = new HashSet<User>(input);
		Set<User> OutputUser = main(set);
		for(User a:OutputUser) {
			for(Integer aa: a.interest) {
				System.out.print(aa+":");
			}
			System.out.println("");
		}

	}

	public void see(Set<User> set) {
		if( set == null ) {
			System.out.println( "This set is null" );
			return;
		}
		for(User a: set) {
			for(Integer aa: a.interest) {
				System.out.print(aa+":");
			}
			System.out.println("#");
		}
			System.out.println("-----------------------");
	}
	public void see(User a) {
		if( a == null ) {
			System.out.println( "This user is null" );
			return;
		}
		for(Integer aa: a.interest) {
			System.out.print(aa+":");
		}
		System.out.println("");
	}
	public void isee( Set<Integer> set ) {
		for( Integer i: set )
			System.out.print( i + ":" );
		System.out.println( "#" );
	}

	public Set<User> main(Set<User> set) {

		if(set.size()==0) {
			return new HashSet<User>();
		}

		set = DelSubUser(set);

		Set<User> unique = UniqueElement(set);
		if( unique.size() > 0 ) {
			set = delete( set, unique );
			unique.addAll(main(set));
			return unique;
		}

		User label = ChooseMaxSizeUser(set);

		if( label.interest.size()==2) {
			return Condition2msc(set);
		}

		set.remove(label);
		Set<User> a = main(set);
		set = delete( set, new HashSet<User>( Collections.singleton( label ) ) );
		Set<User> b = main(set);
		if( a.size() < 1 + b.size() )
			return a;
		else
			return b;
	}

	public Set<User> DelSubUser(Set<User> InputUser) {
		Set<User> ForDelete = new HashSet<>();
		for(User a: InputUser) {
			for(User b: InputUser) {
				if(isSubSet(a.interest,b.interest)&& !a.equals(b)) {
					if(!ForDelete.contains(b)) {
						ForDelete.add(b);
						//System.out.println(a.interest.size()+"::"+b.interest.size());
					}
				}
			}
		}
		for(User del: ForDelete) {
			InputUser.remove(del);
		}
		return InputUser;
	}

	public boolean isSubSet(Set<Integer> a, Set<Integer> b) {

		for(Integer bb : b) {
			if(!a.contains(bb)) {
				return false;
			}
		}
		return true;
	}

	public Set<User> UniqueElement(Set<User> InputUser) {
		Set<Integer> UniqueInterest = new HashSet<>();
		Set<Integer> DualInterest= new HashSet<>();
		Set<User> OutputUser = new HashSet<>();

		for(User a: InputUser) {
			for(Integer aa: a.interest) {
				if(!UniqueInterest.contains(aa)) {
					UniqueInterest.add(aa);
				}else {
					DualInterest.add(aa);
				}
			}
		}
		UniqueInterest.removeAll(DualInterest);
		for(User a: InputUser) {
			for(Integer aa: a.interest) {
				if(UniqueInterest.contains(aa) && !OutputUser.contains(a)) {
					OutputUser.add(a);
				}
			}
		}
		return OutputUser;
	}

	public Set<User> delete( Set<User> user, Set<User> uniq ) {
		Set<User> ret = new HashSet<>();
		Set<Integer> element = new HashSet<>();

		for( User u: uniq )
			for( Integer i: u.interest )
				element.add( i );
		for( User u: user ) {
			User n = new User();
			for( Integer i: u.interest )
				if( !element.contains( i ) )
					n.interest.add( i );
			if( n.interest.size() > 0 )
				ret.add( n );
		}

		return ret;
	}

	public User ChooseMaxSizeUser(Set<User> InputUser) {
		User max = null;
		int size=0;
		for(User user: InputUser) {
			if(user.interest.size()>size) {
				size = user.interest.size();
				max = user;
			}
		}

		return max;
	}
	public Set<User> Condition2msc(Set<User> InputUser) {

		Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
		Set<User> OutputUser = new HashSet<User>();
		
		for(User a: InputUser) {
			Integer[] arr = new Integer[a.interest.size()];
			System.arraycopy(a.interest.toArray(), 0, arr, 0, a.interest.size()); 
			g.addVertex(arr[0]);
			g.addVertex(arr[1]);
			g.addEdge(arr[0], arr[1]);
		}
		
		MatchingImpl m = new MatchingImpl(g,g.edgeSet(),0);
		
		if(m.isPerfect()) {
			for(User a:InputUser) {
				OutputUser.add(a);
			}
			return OutputUser;
		}
		else {
			Set<Integer> insert = new HashSet<Integer>();
			for(Integer v: g.vertexSet()) {
				if(!m.isMatched(v) && !insert.contains(v)) {
					for(User a: InputUser) {
						for(Integer aa: a.interest) {
							if(v.equals(aa)) {
								OutputUser.add(a);
								insert.add(v);
								break;
							}
						}
						if( insert.contains(v) )
							break;
					}
				}
			}
			Set<DefaultEdge> edge = m.getEdges();
			for(DefaultEdge e: edge ) {
				Integer a = g.getEdgeSource(e);
				Integer b = g.getEdgeTarget(e);
				for(User user: InputUser) {
					if(user.interest.contains(a) && user.interest.contains(b)) {
						OutputUser.add(user);
					}
				}
			}
		}
		return OutputUser;
	}
}
