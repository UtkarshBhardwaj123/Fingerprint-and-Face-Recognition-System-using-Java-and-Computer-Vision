package com.vscode.attendance.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.engine.jdbc.BlobProxy;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vscode.common.CommonUtil;
import com.vscode.common.Filter;
import com.vscode.entity.attendance.EmployeeAttendanceBean;
import com.vscode.entity.attendance.EmployeeDetailsBean;
import com.vscode.entity.attendance.StudentAttendanceBean;
import com.vscode.entity.attendance.VectorBean;

/**
 * @author Utkarsh Bhardwaj
 */
@Component
@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private StudentAttendanceRepo studentAttendanceRepo;

	@Autowired
	private StudentDetailsRepo studentDetailsRepo;

	@Autowired
	private EmployeeAttendanceRepo employeeAttRepo;

	@Autowired
	private EmployeeDetailsRepo employeeDetRepo;

	@Autowired
	private VectorRepo vectorRepo;

	@Override
	public List<Map<String, Object>> loadAtt(Filter filter) {
		List<Map<String, Object>> dynaBeans = studentAttendanceRepo.loadAll(filter);

		if (dynaBeans == null)
			return new ArrayList<Map<String, Object>>();
		return dynaBeans;
	}

	@Override
	public List<Map<String, Object>> loadDett(Filter filter) {
		List<Map<String, Object>> dynaBeans = studentDetailsRepo.loadAll(filter);

		if (dynaBeans == null)
			return new ArrayList<Map<String, Object>>();

		return dynaBeans;
	}

	@Override
	public List<Map<String, Object>> loadEmpAtt(Filter filter) {
		List<Map<String, Object>> dynaBeans = employeeAttRepo.loadAll(filter);

		if (dynaBeans == null)
			return new ArrayList<Map<String, Object>>();

		return dynaBeans;
	}
	
	@Override
	public List<Map<String, Object>> loadEmpDett(Filter filter) {
		List<Map<String, Object>> dynaBeans = employeeDetRepo.loadAll(filter);

		if (dynaBeans == null)
			return new ArrayList<Map<String, Object>>();

		return dynaBeans;
	}

	@Override
	public void updateRecords(List<Map<String, Object>> attendedDynaBeans, List<Map<String, Object>> allDynaBeans) {
		if (allDynaBeans != null && attendedDynaBeans != null) {

			studentAttendanceRepo.saveAll(
					(Iterable<StudentAttendanceBean>) convertDynaBeanToAttEntity(attendedDynaBeans, allDynaBeans));
		}
	}

	private List<StudentAttendanceBean> convertDynaBeanToAttEntity(List<Map<String, Object>> dynaBeans,
			List<Map<String, Object>> allDynaBeans) {
		List<StudentAttendanceBean> entityList = new ArrayList<StudentAttendanceBean>();

		List<Integer> rollNos = dynaBeans.stream().map(dynaBean -> Integer.parseInt(dynaBean.get("ROLL_NO").toString()))
				.collect(Collectors.toList());

		allDynaBeans.forEach(dynaBean -> {
			StudentAttendanceBean entity = new StudentAttendanceBean();
//rollnos. will never be null
			if (dynaBean.get("ROLL_NO") != null && dynaBean.get("CLASS") != null && dynaBean.get("SECTION") != null) {

				entity.setName(dynaBean.get("NAME").toString());

				entity.setRollNo(Byte.parseByte(dynaBean.get("ROLL_NO").toString()));
				entity.setBatch(dynaBean.get("CLASS").toString());
				entity.setSection(dynaBean.get("SECTION").toString());

//				if (dynaBean.get("STUDENT_STATUS") != null)
//					entity.setStudentStatus(dynaBean.get("STUDENT_STATUS").toString());

				// increased the no. of days student were present
				Integer rollNo = Integer.parseInt(dynaBean.get("ROLL_NO").toString());

				if (rollNos.contains(rollNo))
					entity.setTotalDaysPresent(Integer.parseInt(dynaBean.get("TOTAL_DAYS_PRESENT").toString()) + 1);

				// increased the no. of days students were absent
				else
					entity.setTotalDaysAbsent(Integer.parseInt(dynaBean.get("TOTAL_DAYS_ABSENT").toString()) + 1);

				entity.settotalDaysOpened(Integer.parseInt(dynaBean.get("TOTAL_DAYS_OPENED").toString()) + 1);

			}
			entityList.add(entity);
		});

		return entityList;
	}

	@Override
	public void addEmployee(Map<String, Object> dynaBean) {
		employeeDetRepo.save(convertDynaBeanToEmpDet(dynaBean));
		employeeAttRepo.save(convertDynaBeanToEmpAtt(dynaBean));
		vectorRepo.saveAll((Iterable<VectorBean>) convertDynaBeanToVector(dynaBean));
//        employeeAttRepo.save((Iterable)Arrays.asList((double[])dynaBean.get("ARRAY_ID")).stream().collect(Collectors.toList()));
	}

	public void updateFingerPrint(Map<String, Object> dynaBean) {
		employeeAttRepo.save(convertDynaBeanToEmpAttendance(dynaBean));
	}

	private EmployeeDetailsBean convertDynaBeanToEmpDet(Map<String, Object> dynaBean) {
		EmployeeDetailsBean entity = new EmployeeDetailsBean();

		entity.setEmployeeId((short) dynaBean.get("EMPLOYEE_ID"));

		if (dynaBean.get("EMPLOYEE_NAME") != null)
			entity.setEmployeeName(dynaBean.get("EMPLOYEE_NAME").toString());

		if (dynaBean.get("FATHERS_NAME") != null)
			entity.setFathersName(dynaBean.get("FATHERS_NAME").toString());

//		if (dynaBean.get("PROBATION_PERIOD") != null)
//			entity.setProbationPeriod(Short.parseShort(dynaBean.get("PROBATION_PERIOD").toString()));

		if (dynaBean.get("DOB") != null)
			entity.setDob(CommonUtil.getDateFromStr(dynaBean.get("DOB").toString()));

		if (dynaBean.get("MOBILE_NO") != null)
			entity.setMobileNo(Long.parseLong(dynaBean.get("MOBILE_NO").toString()));

		if (dynaBean.get("WORKING_STATUS") != null)
			entity.setWorkingStatus(dynaBean.get("WORKING_STATUS").toString());

		if (dynaBean.get("GENDER") != null)
			entity.setGender(dynaBean.get("GENDER").toString());

		if (dynaBean.get("DEPARTMENT") != null)
			entity.setDepartment(dynaBean.get("DEPARTMENT").toString());

		if (dynaBean.get("LOCATION") != null)
			entity.setLocation(dynaBean.get("LOCATION").toString());

		return entity;
	}

	private EmployeeAttendanceBean convertDynaBeanToEmpAttendance(Map<String, Object> dynaBean) {
		EmployeeAttendanceBean entity = convertDynaBeanToEmpAtt(dynaBean);

		entity.setDaysPresent((short) (((Integer) dynaBean.get("DAYS_PRESENT")).shortValue() + 1));
		entity.setWorkingDays((short) (((Integer) dynaBean.get("WORKING_DAYS")).shortValue() + 1));

		return entity;
	}

	private EmployeeAttendanceBean convertDynaBeanToEmpAtt(Map<String, Object> dynaBean) {
		EmployeeAttendanceBean entity = new EmployeeAttendanceBean();

		entity.setFingerPrintId(((Byte) dynaBean.get("FINGERPRINT_ID")).byteValue());
//		byte[] imageArray = DatatypeConverter.parseBase64Binary(dynaBean.get("EMPLOYEE_IMAGE").toString());
		if (dynaBean.get("EMPLOYEE_IMAGE") != null) {
			entity.setEmployeeImage(BlobProxy.generateProxy((byte[]) dynaBean.get("EMPLOYEE_IMAGE")));
			entity.setId(dynaBean.get("INPUT_VECTOR").toString());
		}

		entity.setEmployeeId(((Short) dynaBean.get("EMPLOYEE_ID")).shortValue());

		return entity;
	}

	@Override
	public Map<String, Object> idsAvailable() {
		Map<String, Object> dynaBean = employeeAttRepo.callProcedure();

		if (dynaBean == null)
			return new HashMap<String, Object>();

		return dynaBean;

	}

	@Override
	public Map<String, Object> recognizeFace(Map<String, Object> dynaBean) {
		Map<String, Object> response = new HashMap<String, Object>();
		Filter filter = new Filter();
		filter.addSelectColumn("EMPLOYEE_NAME");
		filter.addSelectColumn("ARRAY_ID");
		filter.addSelectColumn("INDEX");
		filter.addSelectColumn("VALUE");
		filter.setQueryString("Select EMPLOYEE_NAME, ARRAY_ID, INDEX, VALUE from "
				+ "employee_attendance natural join vector sort by ARRAY_ID");

		List<Map<String, Object>> dynaBeans = employeeAttRepo.loadAll(filter);

		dynaBean.put("Distance", Double.MAX_VALUE);
		double[] vector1 = (double[]) dynaBean.get("INPUT_VECTOR");
		double[] vector2 = new double[dynaBeans.size()];
		dynaBeans.forEach(in -> {
			if ((dynaBeans.indexOf(in) == 0) || (in.get("ARRAY_ID").toString()
					.equalsIgnoreCase(dynaBeans.get(dynaBeans.indexOf(in) - 1).get("ARRAY_ID").toString())))
				vector2[((Integer) in.get("INDEX"))] = ((Double) dynaBean.get("VALUE")).doubleValue();
			else {

				double dist = Nd4j.create(vector1).distance2(Nd4j.create(vector2));
				if (dist < ((Double) dynaBean.get("Distance")).doubleValue()) {
					dynaBean.put("Distance", dist);
					response.put("EMPLOYEE_NAME", in.get("EMPLOYEE_NAME"));
				}
			}

		});
		if (((Double) dynaBean.get("Distance")).doubleValue() == Double.MAX_VALUE)
			response.put("EMPLOYEE_NAME", "No one");

		return response;

	}

	private List<VectorBean> convertDynaBeanToVector(Map<String, Object> dynaBean) {
		List<VectorBean> vectorBean = new ArrayList<>();
		Double[] arr = (Double[]) dynaBean.get("INPUT_VECTOR");
		List<Double> list = Arrays.asList(arr).stream().map(in -> in.doubleValue()).collect(Collectors.toList());
		list.forEach(in -> {
			VectorBean vector = new VectorBean(arr.toString(), list.indexOf(in), in);
			vectorBean.add(vector);
		});
		return vectorBean;
	}

//	private List<StudentDetailsBean> convertDynaBeanToEntity(List<Map<String, Object>> dynaBean,
//			StudentDetailsBean entity) {
//		List<StudentDetailsBean> entityList = new ArrayList<StudentDetailsBean>();
//
//		for (Map<String, Object> map : dynaBean) {
//
//			entity.setName(map.get("NAME").toString());
//			entity.setRollNo(Byte.parseByte(map.get("ROLL_NO").toString()));
//			entity.setBatch(map.get("CLASS").toString());
//			entity.setSection(map.get("SECTION").toString());
//			entity.setStdStatus(map.get("STUDENT_STATUS").toString());
//			entity.setFathersName(map.get("FATHERS_NAME").toString());
//			entity.setMothersName(map.get("MOTHERS_NAME").toString());
//			entity.setAdmissionNo(BigInteger.valueOf((Long) map.get("ADMISSION_NO")));
//			if (map.get("ADMISSION_DATE") != null)
//				entity.setAdmissionDate(CommonUtil.getDateFromStr(map.get("ADMISSION_DATE").toString()));
//			entityList.add(entity);
//		}
//
//		return entityList;
//	}

}
