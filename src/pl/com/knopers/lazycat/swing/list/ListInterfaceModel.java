package pl.com.knopers.lazycat.swing.list;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.AbstractListModel;

public class ListInterfaceModel<V> extends AbstractListModel<V>
{
	private static final long serialVersionUID = 5393089608530648168L;
	private List<V> list;

	public ListInterfaceModel(List<V> list)
	{
		this.list = list;
	}
	@Override
	public int getSize()
	{
		return list.size();
	}
	@Override
	public V getElementAt(int index)
	{
		if(index < 0 || index >= list.size())
			throw new NoSuchElementException("at index " + index);
		return list.get(index);
	}
	public boolean add(V element)
	{
		if(list.add(element))
		{
			fireIntervalAdded(this, list.size() - 1, list.size());
			return true;
		}
		return false;
	}
	public <T extends Collection<V>> boolean addAll(T collection)
	{
		int old = list.size();
		if(list.addAll(collection))
		{
			fireIntervalAdded(this, old, list.size());
			return true;
		}
		return false;
	}
	public List<V> getAll()
	{
		return list;
	}
	
	public V remove(int idx)
	{
		V rem = list.remove(idx);
		if(rem != null)
			fireIntervalRemoved(this, idx, idx);
		return rem;
	}
	
	public boolean remove(V obj)
	{
		int idx = list.indexOf(obj);
		if(idx == -1)
			return false;
		
		list.remove(idx);
		fireIntervalRemoved(this, idx, idx);
		return true;
	}
	public void refreshAll()
	{
		fireContentsChanged(this, 0, getSize());
	}
	public void clear()
	{
		int size = list.size() - 1;
		if(size < 0)
			return;
		list.clear();
		fireIntervalRemoved(this, 0, size);
	}
	
}
