package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.ListNotFoundException;
import alkemy.challenge.Challenge.Alkemy.repository.BaseRepository;
import alkemy.challenge.Challenge.Alkemy.service.imp.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alkemy.challenge.Challenge.Alkemy.controller.request.ActivityRequest;
import alkemy.challenge.Challenge.Alkemy.exception.RecordNotExistException;
import alkemy.challenge.Challenge.Alkemy.model.Activity;
import alkemy.challenge.Challenge.Alkemy.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService extends BaseServiceImpl<Activity, Long> {

	@Autowired
	ActivityRepository activityRepository;

	public ActivityService(BaseRepository<Activity, Long> baseRepository) {
		super(baseRepository);
	}

	public Activity deleteActivity(Activity act) {
		act.setSoftDelete(true);
		return this.update(act);
	}

	public Activity findByName(String name) {
		Activity act = activityRepository.findByName(name).orElse(null);
		return act;
	}

	public List<Activity> getAllNotDelete() throws ListNotFoundException {

		List<Activity> list = activityRepository.findAllBySoftDeleteFalseOrderByCreateAt();
		return Optional.ofNullable(list).orElseThrow(() -> new ListNotFoundException("no comment to list"));
	}

	// método set
	public Activity setAct(ActivityRequest actReq, Long id) throws RecordNotExistException {
		Activity activity = this.getById(id);

		if (activity != null) {
			if (actReq.getName() != null) {
				activity.setName(actReq.getName());
			}
			if (actReq.getContent() != null) {
				activity.setContent(actReq.getContent());
			}
			if (actReq.getImage() != null) {
				activity.setImage(actReq.getImage());
			}
		}

		return activity;

	}

}
