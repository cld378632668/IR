package test.Util.SimpleKVDB; 

import Util.SimpleKVDB.SimpleKVDB;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.HashMap;
import java.util.Random;

/** 
* SimpleKVDB Tester. 
* 
* @author <Authors name> 
* @since <pre>, 2018</pre>
* @version 1.0 
*/ 
public class SimpleKVDBTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: put(String key, byte[] value) 
* 
*/ 
@Test
public void testPut() throws Exception {
    SimpleKVDB sDB = new SimpleKVDB("./", "objectsToStoreToKVStores");

    int size = 10000;

    HashMap hashMap = new HashMap((int)(size/0.75f) + 1, 0.75f);

    Random random = new Random();



} 

/** 
* 
* Method: get(String key) 
* 
*/ 
@Test
public void testGet() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: remove(String key) 
* 
*/ 
@Test
public void testRemove() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: close() 
* 
*/ 
@Test
public void testClose() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: loadMeta() 
* 
*/ 
@Test
public void testLoadMeta() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("loadMeta"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: loadGaps(DataInputStream in) 
* 
*/ 
@Test
public void testLoadGaps() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("loadGaps", DataInputStream.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: saveGaps(DataOutputStream out) 
* 
*/ 
@Test
public void testSaveGaps() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("saveGaps", DataOutputStream.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: saveMeta() 
* 
*/ 
@Test
public void testSaveMeta() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("saveMeta"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: saveIndex(DataOutputStream out) 
* 
*/ 
@Test
public void testSaveIndex() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("saveIndex", DataOutputStream.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: loadIndex(DataInputStream in) 
* 
*/ 
@Test
public void testLoadIndex() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("loadIndex", DataInputStream.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getData(long pos) 
* 
*/ 
@Test
public void testGetData() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("getData", long.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: writeData(long pos, byte[] data) 
* 
*/ 
@Test
public void testWriteData() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("writeData", long.class, byte[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: nextAvailablePos() 
* 
*/ 
@Test
public void testNextAvailablePos() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("nextAvailablePos"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: flush() 
* 
*/ 
@Test
public void testFlush() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = SimpleKVDB.getClass().getMethod("flush"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
