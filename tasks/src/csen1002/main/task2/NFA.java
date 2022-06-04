package csen1002.main.task2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Console;
import java.util.Arrays;

import org.junit.jupiter.api.Timeout;

import csen1002.main.task1.DFA;

/**
 * Write your info here
 * 
 * @name Karim Ebrahim Merhom
 * @id 43-0414
 * @labNumber 10
 */
public class NFA{
	/**
	 * NFA constructor
	 * 
	 * @param description is the string describing a NFA
	 */
		String[] nfaSplit ;
		String[] zeroTrans ;
		String[] oneTrans ;
		String[] eTrans ;
		String[] acceptStates ;
		String states;
		String[] statesSplit ;
		String[] statesArray ;
		String[] eClosure ;
	public NFA(String description) {
		// TODO Write Your Code Here
		
	     nfaSplit = description.split("#");
		 zeroTrans = nfaSplit[0].split(";");
		 oneTrans = nfaSplit[1].split(";");
		 eTrans = nfaSplit[2].split(";");
		 acceptStates = nfaSplit[3].split(";");
		 states = "";
		 statesSplit = states.split(",");
		for (int i = 0 ; i < zeroTrans.length ; i++)
		{
			boolean alreadyExists = false;
			String[] zeroTransSplit = zeroTrans[i].split(",");
			statesSplit = states.split(",");
			for(int z = 0 ; z < 2 ; z++)
			{
			for(int j = 0 ; j < statesSplit.length ; j++)
			{
				if(zeroTransSplit[z].equals(statesSplit[j]))	
				{
					alreadyExists = true;
					break;
				}
			}
			if (alreadyExists == false)
			{
				states+=zeroTransSplit[z]+",";
			}
			alreadyExists = false;
			}
			
		}
		for (int i = 0 ; i < oneTrans.length ; i++)
		{
			boolean alreadyExists = false;
			String[] oneTransSplit = oneTrans[i].split(",");
			statesSplit = states.split(",");
			for(int z = 0 ; z < 2 ; z++)
			{
			for(int j = 0 ; j < statesSplit.length ; j++)
			{
				if(oneTransSplit[z].equals(statesSplit[j]))	
				{
					alreadyExists = true;
					break;
				}
			}
			if (alreadyExists == false)
			{
				states+=oneTransSplit[z]+",";
			}
			alreadyExists = false;
			}
			
		}
		for (int i = 0 ; i < eTrans.length ; i++)
		{
			boolean alreadyExists = false;
			String[] eTransSplit = eTrans[i].split(",");
			statesSplit = states.split(",");
			for(int z = 0 ; z < 2 ; z++)
			{
			for(int j = 0 ; j < statesSplit.length ; j++)
			{
				if(eTransSplit[z].equals(statesSplit[j]))	
				{
					alreadyExists = true;
					break;
				}
			}
			if (alreadyExists == false)
			{
				states+=eTransSplit[z]+",";
				
			}
			alreadyExists = false;
			}
			
		}
		
		StringBuffer st= new StringBuffer(states);
		st.deleteCharAt(st.length()-1);  
		states= st.toString();
		statesArray = states.split(",");
		eClosure = states.split(",");
		int change = 0 ; 
		for (int i = 0 ; i < statesArray.length ; i++ )
		{
			for ( int j = 0 ; j < eTrans.length ; j++)
			{
				String[] eTransSplit = eTrans[j].split(",");
				if (eTransSplit[0].equals(statesArray[i]))
				{
					eClosure[i]= eClosure[i] + "," + eTransSplit[1] ;
					change +=1;
				}
			}
		}

        int changeTimes = 0 ; 
		while (changeTimes<=2)
		{
			
		for (int i = 0 ; i < statesArray.length ; i++)
		{

		 String[] eclosureSplit = eClosure[i].split(",");

			 String newEclosure = "" ;

			 for (int j = 0 ; j < eclosureSplit.length ; j ++) 
			 {
				 
				 for ( int z = 0 ; z < statesArray.length ; z++)
				 {
					 if (eclosureSplit[j].equals(statesArray[z]))
					 {
						 if(newEclosure.equals(""))
						 {
						 newEclosure+= eClosure[z];
						 }
						 else
						 {
					     if(!(newEclosure.contains(eClosure[z])))
					     {
						 newEclosure+= "," + eClosure[z];	 
					 }
					}
					 }
				 }
			 }
			 eClosure[i] = newEclosure ;
			
		}
		changeTimes +=1 ;
		}

		for ( int i = 0 ; i < statesArray.length ; i ++)
		{
			System.out.println("states: "+statesArray[i]);
			String[] eClosureSplit = eClosure[i].split(",");
			String uniqueEclosureSplit = "";
			
			for(int j = 0 ; j < eClosureSplit.length ; j ++)
			{
				String[] uniqueEclosureSplitArr = uniqueEclosureSplit.split(",");
				boolean exists = false ;
				for(int m = 0 ; m < uniqueEclosureSplitArr.length ; m ++)
				{
					if(uniqueEclosureSplitArr[m].equals(eClosureSplit[j]))
					{
					 exists = true ;	
					}
				}
				if(!exists)
				{
					uniqueEclosureSplit += eClosureSplit[j] + ",";

				}
			}
			StringBuffer sb= new StringBuffer(uniqueEclosureSplit);  
			sb.deleteCharAt(sb.length()-1);  
			eClosure[i] = sb.toString();
			System.out.println("eClosures: "+eClosure[i] );
		}

	}

	/**
	 * Returns true if the string is accepted by the NFA and false otherwise.
	 * 
	 * @param input is the string to check by the NFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		String[] inputSplit = input.split("");
		String state = "0";
		String newState = "" ;
		String[] stateSplit = state.split(",");
		
		for (int i = 0 ; i < inputSplit.length ; i++ )
		{
			newState = "" ;
			if(inputSplit[i].equals("0"))
			{
				for(int z = 0 ; z < stateSplit.length ; z++)
				{
					
					for (int m = 0 ; m < statesArray.length;m++)
					{
						if(stateSplit[z].equals(statesArray[m]))
						{
							String [] stateClosure = eClosure[m].split(",");
							
							for(int x = 0 ; x < stateClosure.length ; x ++)
							{
								
								for(int c = 0 ; c < zeroTrans.length ; c ++)
								{
									String[] zeroSplit = zeroTrans[c].split(",");
									
									if(zeroSplit[0].equals(stateClosure[x]))
									{	
										for(int d = 0 ;  d < statesArray.length ; d++ )
										{
											if(statesArray[d].equals(zeroSplit[1]))
											{
											
													String[] eClosureSplit = eClosure[d].split(",");
													for(int t = 0 ; t < eClosureSplit.length ; t ++)
													{
													
															if(!newState.contains(eClosureSplit[t]))
															{
																newState+= eClosureSplit[t] + ",";
															}
														
													}
													
												
										
											}
										}
										
									}
								}
							}
						}
					}
				}
			}
			
			if(inputSplit[i].equals("1"))
			{
				
				for(int z = 0 ; z < stateSplit.length ; z++)
				{
					
					for (int m = 0 ; m < statesArray.length;m++)
					{
						if(stateSplit[z].equals(statesArray[m]))
						{
							String [] stateClosure = eClosure[m].split(",");
							for(int x = 0 ; x < stateClosure.length ; x ++)
							{
								
								for(int c = 0 ; c < oneTrans.length ; c ++)
								{
									String[] oneSplit = oneTrans[c].split(",");
									
									if(oneSplit[0].equals(stateClosure[x]))
									{		
										for(int f = 0 ; f < statesArray.length; f++)
										{
											if( oneSplit[1].equals(statesArray[f]))
											{
												String[] eClosureSplit = eClosure[f].split(",");
												for(int t = 0 ; t < eClosureSplit.length ; t ++)
												{
												
														if(!newState.contains(eClosureSplit[t]))
														{
															newState+= eClosureSplit[t] + ",";
														}
													
												}
																					
											}
										}
										
									}
								}
							}
						}
					}
				}
			}
			StringBuffer ss= new StringBuffer(newState); 
			if(ss.length() != 0)
			{
				ss.deleteCharAt(ss.length()-1);  
				state =  ss.toString();
			}
			else
			{
				state = "phi";
			}
			stateSplit = state.split(",");
			
		}	
		
		
		 stateSplit = state.split(",");
		 for(int i = 0 ; i < stateSplit.length ; i ++ )
		 {
			 for(int j = 0 ; j < acceptStates.length ; j ++ )
			 {
				 if(stateSplit[i].equals(acceptStates[j]))
				 {
					 System.out.println("true");
					 return true;
				 }
			 }
		 }
		 System.out.println("false");
		return false;
	}
	
	public static void main (String[] args)
	{
		NFA NFA1 = new NFA("0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2;0,1;3,1#3");
		NFA1.run("01111111");
		
	}
}
