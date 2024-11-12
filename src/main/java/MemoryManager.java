public class MemoryManager
{
   protected MemoryAllocation sentinel;
   public MemoryAllocation head;
    
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   public MemoryManager(long size)
   {
	   sentinel = new MemoryAllocation("Sentinel", (long) 0, (long) 0, null, null);
	   head = new MemoryAllocation(Free, (long) 0, (long) size, sentinel, sentinel);
	   sentinel.next = head;
	   sentinel.prev = head;
	   
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester) {
	   	if(size <= 0) { return null;}
    	  MemoryAllocation curr = head;
    	  while (curr != sentinel) {
    		  if(curr.owner.equals(Free) && curr.len >= size) {
    			  MemoryAllocation newMemory = new MemoryAllocation(requester,curr.pos, size, curr, curr.prev);
    	    	  curr.prev = newMemory;
    	    	  curr.prev.next = curr;
    	    	  curr.pos += size;
    	    	  curr.len -= size;
    	    	  return newMemory;
    		  }
    		  curr = curr.next;
    	  }  
    	  return null;
} 
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   mem.owner = Free;
	   if(mem.next.owner.equals(Free)) {
		   mem.len += mem.next.len;
		   mem.next = mem.next.next;
		   mem.prev.next = mem;
	   }
	   if(mem.prev.owner.equals(Free)) {
		   mem.prev.len += mem.len;
		   mem.prev.next = mem.next;
		   mem.next.prev = mem.prev;
	   }  
   }
}
