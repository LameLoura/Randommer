package pongp.random.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pongp.random.RandomPicker.RandomPicker;

public class ExampleApplication
{

	public static void main( String[] args )
	{
		OutputTracer tarcker = new OutputTracer();
		
		List<String> stringList = new ArrayList<String>();
		stringList.add( "A" );
		stringList.add( "B" );
		stringList.add( "C" );
		
		RandomPicker < String > picker = new RandomPicker<>( stringList );		
		
		//while( true )
		for( int a = 0 ; a< 10_000; a++ )
		{
			String ans = "";

			picker.reset();
			for( int i =0; i < stringList.size(); i++ )
			{
				ans += picker.getNextRandom();
			}
			
			//the total sequence
			System.out.println( ans );
			tarcker.onNewValuePopulated( ans );
		}
		
		System.out.println( tarcker );
	}
	
	public static class OutputTracer
	{
		int allCount = 0;
		final HashMap < String , Integer > outputCount = new HashMap< String , Integer >();
		
		public void onNewValuePopulated( String valuee )
		{
			Integer count = outputCount.get( valuee );
			if( count == null )
			{
				count = new Integer( 0 );
				outputCount.put( valuee , count );
			}
			
			count = count.intValue() + 1;
			outputCount.put( valuee , count );
			allCount++;
		}
		
		@Override
		public String toString()
		{
			String ans = "";
			double max = 0, min = 100;
			for( String value : outputCount.keySet() )
			{
				int count = outputCount.get( value );
				double percent = (double)count/(double)allCount * 100;
				if( percent > max )
				{
					max = percent;
				}
				if( percent < min )
				{
					min = percent;
				}
				ans += String.format( "Value : %s \tcount = %s ( %.2f %% )\n" , value, count, percent );
			}
			//ans +=  String.format( "Versatility = %.2f ( min = %.2f, max = %.2f )\n" , max - min, min, max );
			return ans;
		}
		
	}

}
