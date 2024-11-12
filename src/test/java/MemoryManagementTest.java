import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryManagementTest {
	@Test
	final void MemoryStoragetest() {
		MemoryManager mem = new MemoryManager((long) 100);
		MemoryAllocation testm = mem.requestMemory((long)110, "Jansen");
		MemoryAllocation testm2 = mem.requestMemory((long)80, "Robert");
		MemoryAllocation testm3 = mem.requestMemory((long)30, "Mitch");
		MemoryAllocation testm4 = mem.requestMemory((long) 20, "Chet");
		assertEquals(null, testm);
		assertEquals(testm2.getPosition(), 0);
		assertEquals(testm2.getLength(), 80);
		assertEquals(testm2.getOwner(), "Robert");
		assertEquals(null, testm3);
		assertEquals(80, testm4.getPosition());
		mem.returnMemory(testm2);
		mem.returnMemory(testm4);
		MemoryAllocation testm5 = mem.requestMemory((long) 100, "Rosemary");
		assertEquals(0, testm5.getPosition());	
		mem.returnMemory(testm5);
		MemoryAllocation testm6 = mem.requestMemory((long)20, "Jenna");
		MemoryAllocation testm7 = mem.requestMemory((long)50, "Justin");
		MemoryAllocation testm8 = mem.requestMemory((long)30, "Garret");
		assertEquals(testm6.getPosition(), 0);
		assertEquals(testm7.getPosition(), 20);
		assertEquals(testm8.getPosition(), 70);
		mem.returnMemory(testm7);
		MemoryAllocation testm9 = mem.requestMemory((long)15, "Bradshaw");
		assertEquals(testm6.getPosition(), 0);
		assertEquals(testm9.getPosition(), 20);
		assertEquals(testm8.getPosition(), 70);
		mem.returnMemory(testm9);
		mem.returnMemory(testm8);
		MemoryAllocation testm10 = mem.requestMemory((long)75, "Dewey");
		assertEquals(20, testm10.getPosition());
		mem.returnMemory(testm6);
		mem.returnMemory(testm10);
		MemoryAllocation testm11 = mem.requestMemory((long)30, "Joe");
		MemoryAllocation testm12 = mem.requestMemory((long)60, "Leah");
		MemoryAllocation testm13 = mem.requestMemory((long)10, "Stella");
		mem.returnMemory(testm11);
		mem.returnMemory(testm13);
		mem.returnMemory(testm12);
		MemoryAllocation testm14 = mem.requestMemory((long)100, "Baxter");
		assertEquals(0, testm14.getPosition());
		

	}
}
