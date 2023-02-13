package org.devs.business.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.devs.business.exception.GroupNotFoundException;
import org.devs.business.mapper.GroupMapper;
import org.devs.business.model.dto.GroupDto;
import org.devs.business.model.request.CreateGroupRequest;
import org.devs.business.service.GroupService;
import org.devs.crm.dao.CourseDao;
import org.devs.crm.dao.GroupDao;
import org.devs.crm.dao.MentorDao;
import org.devs.crm.dao.StudentDao;
import org.devs.crm.entity.Course;
import org.devs.crm.entity.Group;
import org.devs.crm.entity.Mentor;
import org.devs.crm.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private final StudentDao studentDao;
    private final MentorDao mentorDao;
    private final CourseDao courseDao;
    private final GroupMapper groupMapper;

    @Override
    @Transactional
    public GroupDto create(CreateGroupRequest req) {

        if (req == null || req.getGroupName().isEmpty() || req.getCourseId() == null || req.getMentorsIds().isEmpty()) {
            throw new IllegalArgumentException();
        }

        List<Student> students = req.getStudentsIds().stream()
                .map(id -> studentDao.findById(id).orElseThrow(RuntimeException::new)).collect(Collectors.toList());

        List<Mentor> mentors = req.getMentorsIds().stream()
                .map(id -> mentorDao.findById(id).orElseThrow(RuntimeException::new)).collect(Collectors.toList());

        Course course = courseDao.findById(req.getCourseId()).orElseThrow(RuntimeException::new);

        Group group = Group.builder()
                .groupName(req.getGroupName())
                .students(students)
                .course(course)
                .mentors(mentors)
                .startDate(req.getStartDate())
                .build();

        groupDao.save(group);

        return groupMapper.toDto(group);
    }

    @Override
    public GroupDto getOne(Long id) {
        return groupMapper.toDto(groupDao.findById(id).orElseThrow(() -> new GroupNotFoundException("For id=" + id)));
    }
}
