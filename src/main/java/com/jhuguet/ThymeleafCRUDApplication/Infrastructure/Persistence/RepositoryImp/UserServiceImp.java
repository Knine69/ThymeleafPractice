package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.RepositoryImp;

import com.jhuguet.ThymeleafCRUDApplication.Domain.Model.User;
import com.jhuguet.ThymeleafCRUDApplication.Domain.repository.UserService;
import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Entity.UserEntity;
import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImp implements UserService, ApplicationEventPublisherAware {

    @Autowired
    private UserRepository userRepository;
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Override
    public List<UserEntity> getAllUsers() throws RuntimeException {
        publisher.publishEvent(new UserServiceEvent(this, "Called getAllUsers Event"));
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserEntity user) throws RuntimeException {
        publisher.publishEvent(new UserServiceEvent(this, "Called addUser Event"));
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) throws RuntimeException {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(!optional.isPresent()) throw new RuntimeException("User not found");
        UserEntity user = optional.get();
        publisher.publishEvent(new UserServiceEvent(this, "Called getUserById Event"));
        return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public void deleteUser(long id) throws RuntimeException{
        publisher.publishEvent(new UserServiceEvent(this, "Called deleteUser Event"));
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);
        publisher.publishEvent(new UserServiceEvent(this, "Called findPaginated Event"));
        return userRepository.findAll(pageable);
    }

}
