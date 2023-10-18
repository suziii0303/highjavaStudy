package kr.or.ddit.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisSqlSessionFactory;
import kr.or.ddit.vo.FileInfoVO;

public class FileInfoDaoImpl implements IFileInfoDao {
	
	private static FileInfoDaoImpl dao;
	private FileInfoDaoImpl() {}
	
	public static FileInfoDaoImpl getInstance() {
		if(dao==null) dao = new FileInfoDaoImpl();
		return dao;
	}

	@Override
	public int insertFileinfo(FileInfoVO fileVO) {
		int cnt = 0;	//반환값이 저장될 변수
		SqlSession session = null;
		
		try {
			session = MyBatisSqlSessionFactory.getSqlSession();
			
			cnt = session.insert("fileinfo.insertFileinfo",fileVO);
			
			if(cnt>0)session.commit();
		} finally {
			if(session!=null)session.close();
		}
		
		return cnt;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		List<FileInfoVO> fileList = null;
		SqlSession session =null;
		
		try {
			session = MyBatisSqlSessionFactory.getSqlSession();
			
			fileList = session.selectList("fileinfo.getAllFileinfo");
		} finally {
			if(session!=null)session.close();
		}
		return fileList;
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		FileInfoVO fileVo = null;
		SqlSession session = null;
		
		try {
			session = MyBatisSqlSessionFactory.getSqlSession();
			
			fileVo = session.selectOne("fileinfo.getFileinfo", fileNo);
		} finally {
			if(session!=null)session.close();
		}
								
		return fileVo;
	}
	
}
