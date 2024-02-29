package com.tca;

import java.io.BufferedReader;
import java.io.FileReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tca.entities.Student;

public class App 
{
    public static void main( String[] args )throws Exception
    {
    	Configuration configuration = new Configuration();
        configuration.configure(); //Load and parse cfg.xml file
        
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        Session session = sessionFactory.openSession();
        
        Transaction txn = session.beginTransaction();
        
        FileReader fr = new FileReader("D:\\Study\\Java\\Hibernate\\CsvToDB\\src\\main\\resources\\Data.csv");
        BufferedReader br = new BufferedReader(fr);
        
        String str = null;
        
        
        while(true)
        {
        	str = br.readLine();
        	if(str==null)
        	{
        		break;
        	}
        	
        	String s[] = str.split(",");
    		Student sob = new Student();
    		
    		sob.setRno(Integer.parseInt(s[0]));
    		sob.setName(s[1]);
    		sob.setPer(Double.parseDouble(s[2]));
    		
    		session.save(sob);
        }
        
       
        txn.commit();
        
        session.close();
        sessionFactory.close();
        
        System.out.println("First Hibernate Done !");
    }
}
