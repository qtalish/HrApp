package com.kgate.service;

import java.util.Date;
import java.util.List;

import com.kgate.entity.HrCallingSheet;

public interface HrCallingSheetService {
	public void saveReport(HrCallingSheet hrCallingSheet);

	List<HrCallingSheet> findByUpdatedDateAndEmpCode(Date date,String empCode);
}
