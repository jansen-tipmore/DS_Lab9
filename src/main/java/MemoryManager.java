public class MemoryManager
{
   protected MemoryAllocation sentinel;
   protected MemoryAllocation head;
    
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   public MemoryManager(long size)
   {
	   MemoryAllocation sentinel = new MemoryAllocation("Sentinel", (long) 0, (long) 0, null, null);
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
	   
	   if (size > head.len) { 
		   return null;
	   }
    	  MemoryAllocation curr = head;
    	  while (curr.owner != Free && curr.len < size) {
    		  if(curr.next == sentinel) {
    			  return null;
    		  }
    		  curr = curr.next;
    	  }
    	  MemoryAllocation newMemory = new MemoryAllocation(requester,curr.pos, size, curr.next, curr);
    	  curr.next.prev = newMemory;
    	  curr.next = newMemory;
    	  curr.len -= size;
    	  return newMemory;
} 
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   if(mem.next.owner == Free && mem.prev.owner == Free) {
		new MemoryAllocation(Free, mem.prev.pos, (mem.prev.len + mem.len + mem.next.len), mem.next.next, mem.prev.prev);
	   }
	   if(mem.next.owner == Free && mem.prev.owner != Free) {
		   new MemoryAllocation(Free, mem.pos, (mem.len + mem.next.len), mem.next.next, mem.prev);
	   }
	   if(mem.prev.owner == Free && mem.next.owner != Free) {
		   new MemoryAllocation(Free, mem.prev.pos, (mem.len + mem.prev.len), mem.next, mem.prev.prev);
	   }
	   else {
	   mem.owner = Free;
	   }   
   }
}
