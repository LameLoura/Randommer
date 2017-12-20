package pongp.random.RandomPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomPicker<T>
{
	
	private final ArrayList<T> list = new ArrayList<T>();
	
	Random randomizer = new Random();
	
	/**
	 * Determine whether an object can be re-randomly re-picked
	 */
	boolean isRepeatable = false;

	//private final ArrayList<T> unpickedItem = new ArrayList<T>();
	private final ArrayList<T> pickedItem = new ArrayList<T>();
	
	public RandomPicker( List<T> itemList )
	{
		//list = itemList;
		for( T item : itemList )
		{
			list.add( item );
		}
		
	}
	
	private T pickAnItem( int index )
	{
		T pickItem = list.get( index );
		onItemPicked( pickItem );
		return pickItem;
	}
	
	/**
	 * Get the next random item
	 * @return
	 */
	public T getNextRandom()
	{
		int ranomedIndex = randomizer.nextInt( list.size() );
		return pickAnItem(ranomedIndex );
	}
	
	public T getFirst()
	{
		return pickAnItem( 0 );
	}
	
	public void onItemPicked( T pickedOne )
	{
		//fuck it, you inconsitent bastard 
		//TODO creawte a poly-something class for picker or something
		if( isRepeatable )
		{
			return;
		}
		
		//cool
		//T pcikedItem = list.get( pickedIndex );
		list.remove( pickedOne );
		pickedItem.add( pickedOne );
		
	}
	
	public void reset()
	{
		for( T item : pickedItem )
		{
			list.add( item );
		}
		
		pickedItem.clear();
	}
	
}
