package backend;
class Rack{

	private int X;
	private int Y;
	private int Z;
	private float MaxWeight;

	public Rack(int x, int y, int z, float maxWeight){
		this.X = x;
		this.Y = y;
		this.Z = z;
		this.MaxWeight = maxWeight;
	}

	public int getPosition(char c){
		
		if(Character.toUpperCase(c) == 'X'){
			return this.X;
		}else if (Character.toUpperCase(c) == 'Y'){
			return this.Y;
		}else if (Character.toUpperCase(c) == 'Z'){
			return this.Z;
		}else{
			return -1;
		}
	}

	public float getMaxWeight(){
		
		return this.MaxWeight;
	}

	public int setPostion(User u, char c, int newPosition){

		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/

		if(Character.toUpperCase(c) == 'X'){
			this.X = newPosition;
			return 0;
		}else if (Character.toUpperCase(c) == 'Y'){
			this.Y = newPosition;
			return 0;
		}else if (Character.toUpperCase(c) == 'Z'){
			this.Z = newPosition;
			return 0;
		}else{
			return -1;
		}

	}

	public int setMaxWeight (User u, float newMaxWeight){

		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/

		this.MaxWeight = newMaxWeight;
		return 0;
	}
}