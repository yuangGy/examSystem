package com.gy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gy.dao.ExamMainMapper;
import com.gy.dao.TeacherExamMapper;
import com.gy.model.Answer;
import com.gy.model.Options;
import com.gy.model.Question;
import com.gy.model.Subject;
import com.gy.model.TeacherExam;
import com.gy.service.AnswerService;
import com.gy.service.ExamMainService;
import com.gy.service.OptionsService;
import com.gy.service.QuestionService;

@Service
public class ExamMainServiceImpl implements ExamMainService {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private ExamMainMapper examMainMapper;
	@Autowired
	private TeacherExamMapper teacherExamMapper;

	@Override
	public List<Subject> findAllSimpleSucjects() {
		return examMainMapper.findAllSimpleSucjects();
	}

	@Override
	public List<Subject> findAllManySucjects() {
		return examMainMapper.findAllManySucjects();
	}

	@Override
	public List<Subject> sysFindAllSimpleSucjects() {
		return examMainMapper.sysFindAllSimpleSucjects();
	}

	@Override
	public List<Subject> sysFindAllManySucjects() {
		return examMainMapper.sysFindAllManySucjects();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addNewQuestion(String type, String questionScore, String questionName, String[] sysoption,
			String[] optionsDesc) {
		Question question = new Question();
		question.setQuestionName(questionName);
		question.setQuestionScore(Integer.parseInt(questionScore));
		if ("单选题".equals(type.trim())) {
			question.setQuestuonType(0);
		} else {
			question.setQuestuonType(1);
		}

		questionService.addQuestion(question);
		int questionId = question.getQuestionId();
		Options options = new Options();
		char flag = 'A';
		for (String string : optionsDesc) {
			options.setOptionsDesc(string);
			options.setOptionsFlag("" + flag++);
			options.setQuestionId(questionId);
			optionsService.addOptions(options);
		}
		Answer answer = new Answer();
		for (String string : sysoption) {
			answer.setQuestionId(questionId);
			answer.setAnswerFlag(string);
			answerService.addAnswer(answer);
		}
		return "yes";
	}

	@Override
	public Subject sysFindSimpleSucject(int questionId) {
		return examMainMapper.sysFindSimpleSucject(questionId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String updateQuestion(String type, String questionId, String questionScore, String questionName,
			String[] sysoption, String[] optionsDesc) {
		Question question = new Question();
		question.setQuestionId(Integer.parseInt(questionId));
		question.setQuestionName(questionName);
		question.setQuestionScore(Integer.parseInt(questionScore));
		if ("单选题".equals(type.trim())) {
			question.setQuestuonType(0);
		} else {
			question.setQuestuonType(1);
		}
		questionService.updateQuestion(question);
		optionsService.deleteAllOptionsByQuestionId(Integer.parseInt(questionId));
		Options options = new Options();
		char flag = 'A';
		for (String string : optionsDesc) {
			options.setOptionsDesc(string);
			options.setOptionsFlag("" + flag++);
			options.setQuestionId(Integer.parseInt(questionId));
			optionsService.addOptions(options);
		}
		answerService.deleteAllAnswerByQuestionId(Integer.parseInt(questionId));
		Answer answer = new Answer();
		for (String string : sysoption) {
			answer.setQuestionId(Integer.parseInt(questionId));
			answer.setAnswerFlag(string);
			answerService.addAnswer(answer);
		}
		return "yes";
	}

	@Override
	public void deleteQuestion(String questionId) {
		int q = Integer.parseInt(questionId);
		questionService.deleteQuestion(q);
		optionsService.deleteAllOptionsByQuestionId(q);
		answerService.deleteAllAnswerByQuestionId(q);
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean saveQuestions(String simpleScore, String manyScore) {
		int simpleScore1 = 0;
		int manyScore1 = 0;
		int flag = 0;
		List<Question> subjects1 = questionService.selectAllQuestionRamd();
		List<Question> subjects2 = new ArrayList<>();
		for (Question subject : subjects1) {
			int temp = simpleScore1;
			simpleScore1 += subject.getQuestionScore();
			if(simpleScore1<Integer.parseInt(simpleScore)) {
				subjects2.add(subject);
			}else if(simpleScore1==Integer.parseInt(simpleScore)) {
				subjects2.add(subject);
				break;
			}else {
				simpleScore1=temp;
			}
		}
		int type = teacherExamMapper.selectMaxType()+1;
		for (Question subject : subjects2) {
			TeacherExam teacherExam = new TeacherExam();
			teacherExam.setQuestionId(subject.getQuestionId());
			teacherExam.setQuestionType(subject.getQuestuonType());
			teacherExam.setType(type);
			teacherExamMapper.insertSelective(teacherExam);
		}
		
		List<Question> subjects3 = questionService.selectAllQuestionRamdmany();
		List<Question> subjects4 = new ArrayList<>();
		for (Question subject : subjects3) {
			int temp = manyScore1;
			manyScore1 += subject.getQuestionScore();
			if(manyScore1<Integer.parseInt(manyScore)) {
				subjects4.add(subject);
			}else if(manyScore1==Integer.parseInt(manyScore)) {
				subjects4.add(subject);
				break;
			}else {
				manyScore1=temp;
			}
		}
		for (Question subject : subjects4) {
			TeacherExam teacherExam = new TeacherExam();
			teacherExam.setQuestionId(subject.getQuestionId());
			teacherExam.setQuestionType(subject.getQuestuonType());
			teacherExam.setType(type);
			flag = teacherExamMapper.insertSelective(teacherExam);
		}
		if (flag>0) {
			
			return true;
		}
		return false;
	}

}
