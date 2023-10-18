package kr.or.ddit.fileupload.service;

import java.util.List;

import kr.or.ddit.fileupload.dao.FileInfoDaoImpl;
import kr.or.ddit.fileupload.dao.IFileInfoDao;
import kr.or.ddit.vo.FileInfoVO;

public class FileInfoServiceImpl implements IFileInfoService {
	private IFileInfoDao dao;
	private static FileInfoServiceImpl service;
	
	private FileInfoServiceImpl() {
		dao = FileInfoDaoImpl.getInstance();
	}
	public static FileInfoServiceImpl getInstance() {
		if(service==null) service = new FileInfoServiceImpl();
		return service;
	}

	@Override
	public int insertFileinfo(FileInfoVO fileVO) {
		return dao.insertFileinfo(fileVO);
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		return dao.getAllFileinfo();
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		return dao.getFileinfo(fileNo);
	}

}
