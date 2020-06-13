package com.sample.postgress.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.sample.postgress.entity.Employee;
import com.sample.postgress.entity.Feedback;
import com.sample.postgress.entity.Sso;
import com.sample.postgress.entity.Users;
import com.sample.postgress.entity.Vote;
import com.sample.postgress.mapper.EmployeeRowMapper;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	public EmployeeDaoImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
}  
	NamedParameterJdbcTemplate template;  
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);



//	@Override
//	public List<Employee> findAll() {
//		return template.query("select * from employee", new EmployeeRowMapper());
//	}
//	
	@Override
	public List<Employee> findAll() {
		
		return jdbcTemplate.query("select * from employee", new ResultSetExtractor<List<Employee>>() {
			@Override
			public List<Employee> extractData(final ResultSet rs) {
				List<Employee> list = new ArrayList<Employee>();
				System.out.print("here1");
				try {
					System.out.print("--->"+rs.getFetchSize());
					while (rs.next()) {
						System.out.print("here2");
						Employee emp = new Employee();
						System.out.print("here3");
						emp.setEmployeeName(rs.getString(1));
						System.out.print("here3");
						emp.setEmployeeId(rs.getString(2));
						emp.setEmployeeAddress(rs.getString(3));
						emp.setEmployeeEmail(rs.getString(4));
//						user.setSso(rs.getString(0));
//						user.setAttended(rs.getBoolean(1));
						System.out.print("here3");
//						
						list.add(emp);
					}
				} catch (SQLException e) {
					System.out.print(e.getMessage());
//					System.out.print(e.printStackTrace());
//					log.error("something went wrong while iterating Engineering Statistics download:"
//							+ e.getMessage());
					System.out.print("something went wrong while iterating");
//					throw new ServerErrorException(ErrorCode.INTERNAL.getResponseStatus(), e);
					
				}
				return list;
			}
			
			});
			
		
//		return null;
	}
	


	@Override
	public List<Users> getAllsso() {
		
		return jdbcTemplate.query("select * from users", new ResultSetExtractor<List<Users>>() {
			@Override
			public List<Users> extractData(final ResultSet rs) {
				List<Users> list = new ArrayList<Users>();
				try {
					while (rs.next()) {
						Users user = new Users();
						
						user.setSso(rs.getString(1));
						user.setAttended(rs.getBoolean(2));
						
						list.add(user);
					}
				} catch (SQLException e) {
//					log.error("something went wrong while iterating Engineering Statistics download:"
//							+ e.getMessage());
					System.out.print("something went wrong while iterating");
//					throw new ServerErrorException(ErrorCode.INTERNAL.getResponseStatus(), e);
					
				}
				return list;
			}
			
			});
			

	}



	@Override
	public void validateAndUpdate(String sso) {
		// TODO Auto-generated method stub
		
		String sql = "select * from users where sso=?";
		String ssoFromDb = "";
		
		SqlRowSet rowval = jdbcTemplate.queryForRowSet(sql, new Object[] { sso  });
		
		
		while(rowval.next()) {
			System.out.print("-----**s");
			System.out.print("****************"+rowval.getString(1));
			ssoFromDb=rowval.getString(1);
			
		}
		
		
		String updateSql="update users set attended='true' where sso=?";
		
		if(sso.equalsIgnoreCase(ssoFromDb)) {
			jdbcTemplate.update(updateSql, new Object[] { sso  });
		}
		
		
	}



	@Override
	public void pollVotes(Vote vote) {
		
		
		String sql = "select * from event_ratings where sso=?";
		String ssoFromDb = "";
		
		SqlRowSet rowval = jdbcTemplate.queryForRowSet(sql, new Object[] { vote.getSso()  });
		
		
		while(rowval.next()) {
			System.out.print("-----**s");
			System.out.print("****************"+rowval.getString(1));
			ssoFromDb=rowval.getString(1);
			
		}

		System.out.print("-------------------------------");			
			
		if(ssoFromDb.equals("")) {
			Random ran = new Random(); 
			
			int val = ran.nextInt(1000);
			System.out.print("--------"+val);			
			String insertSql = "insert into event_ratings values(?,?,?,?)";			
			jdbcTemplate.update(insertSql, new Object[] { val,vote.getEventId(),vote.getSso(),vote.getRating() });
			
		}
		else {
			String updateSql = "update event_ratings set rating=? where sso=?";
			jdbcTemplate.update(updateSql, new Object[] { vote.getRating(),vote.getSso() });
	
		}

		
	}



	@Override
	public Vote getVotes(String sso) {
		
		String sql = "select * from event_ratings where sso=?";
		String ssoFromDb = "";
		
		SqlRowSet rowval = jdbcTemplate.queryForRowSet(sql, new Object[] { sso  });
		
		Vote vote = new Vote();		
		while(rowval.next()) {

			System.out.print("-----**s");
			System.out.print("****************"+rowval.getString(1));
//			ssoFromDb=rowval.getString(1);
			vote.setSso(rowval.getString(2));
			vote.setEventId(rowval.getInt(3));
			vote.setRating(rowval.getInt(4));
			
		}
		
		return vote;
		
	}



	@Override
	public void saveFeedback(Feedback feedback) {
		String sql = "select * from public.event_feedback where sso=?";
		String ssoFromDb = "";
		
		SqlRowSet rowval = jdbcTemplate.queryForRowSet(sql, new Object[] { feedback.getSso()  });
		
		
		while(rowval.next()) {
			System.out.print("-----**s");
			System.out.print("****************"+rowval.getString(1));
			ssoFromDb=rowval.getString(1);
			
		}

		System.out.print("-------------------------------");	
		
		if(ssoFromDb.equals("")) {		
			String insertSql = "insert into event_feedback values(?,?,?)";			
			jdbcTemplate.update(insertSql, new Object[] { feedback.getSso(),feedback.getRating(),feedback.getFeedback() });
			
		}
		else {
			String updateSql = "update event_feedback set description=?, rating=? where sso=?";
			jdbcTemplate.update(updateSql, new Object[] { feedback.getFeedback(),feedback.getRating(),feedback.getSso() });
	
		}
		
		
	}



	@Override
	public Feedback getFeedback(String sso) {
		
		String sql = "select * from event_feedback where sso=?";
		
		SqlRowSet rowval = jdbcTemplate.queryForRowSet(sql, new Object[] { sso  });
		
		
		Feedback feedback = new Feedback();
		
		while(rowval.next()) {

			System.out.print("-----**s");
			System.out.print("****************"+rowval.getString(1));
			feedback.setSso(rowval.getString(1));
			feedback.setRating(rowval.getInt(2));
			feedback.setFeedback(rowval.getString(3));
			
		}
		
		return feedback;

	}
		
	
	
	
	
	
	
//	@Override
//	public void insertEmployee(Employee emp) {
//		 final String sql = "insert into employee(employeeId, employeeName , employeeAddress,employeeEmail) values(:employeeId,:employeeName,:employeeEmail,:employeeAddress)";
//		 
//	        KeyHolder holder = new GeneratedKeyHolder();
//	        SqlParameterSource param = new MapSqlParameterSource()
//					.addValue("employeeId", emp.getEmployeeId())
//					.addValue("employeeName", emp.getEmployeeName())
//					.addValue("employeeEmail", emp.getEmployeeEmail())
//					.addValue("employeeAddress", emp.getEmployeeAddress());
//	        template.update(sql,param, holder);
//	 
//	}
//	
//	@Override
//	public void updateEmployee(Employee emp) {
//		 final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";
//		 
//	        KeyHolder holder = new GeneratedKeyHolder();
//	        SqlParameterSource param = new MapSqlParameterSource()
//					.addValue("employeeId", emp.getEmployeeId())
//					.addValue("employeeName", emp.getEmployeeName())
//					.addValue("employeeEmail", emp.getEmployeeEmail())
//					.addValue("employeeAddress", emp.getEmployeeAddress());
//	        template.update(sql,param, holder);
//	 
//	}
//	
//	@Override
//	public void executeUpdateEmployee(Employee emp) {
//		 final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";
//			 
//
//		 Map<String,Object> map=new HashMap<String,Object>();  
//		 map.put("employeeId", emp.getEmployeeId());
//		 map.put("employeeName", emp.getEmployeeName());
//		 map.put("employeeEmail", emp.getEmployeeEmail());
//		 map.put("employeeAddress", emp.getEmployeeAddress());
//	
//		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
//			    @Override  
//			    public Object doInPreparedStatement(PreparedStatement ps)  
//			            throws SQLException, DataAccessException {  
//			        return ps.executeUpdate();  
//			    }  
//			});  
//
//	 
//	}
//	
//	@Override
//	public void deleteEmployee(Employee emp) {
//		 final String sql = "delete from employee where employeeId=:employeeId";
//			 
//
//		 Map<String,Object> map=new HashMap<String,Object>();  
//		 map.put("employeeId", emp.getEmployeeId());
//	
//		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
//			    @Override  
//			    public Object doInPreparedStatement(PreparedStatement ps)  
//			            throws SQLException, DataAccessException {  
//			        return ps.executeUpdate();  
//			    }  
//			});  
//
//	 
//	}
	
}
