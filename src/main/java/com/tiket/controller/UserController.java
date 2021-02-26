package com.tiket.controller;

import com.tiket.common.ApiPath;
import com.tiket.entity.CommonResponse;
import com.tiket.entity.ResponseCode;
import com.tiket.entity.User;
import com.tiket.entity.request.MandatoryRequest;
import com.tiket.entity.response.BaseResponse;
import com.tiket.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping(ApiPath.USER)
@ApiOperation(value = "user Controller")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "find all", notes = "find all")
	@GetMapping(ApiPath.FIND_ALL)
	public Mono<BaseResponse<List<User>>> findAll(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest) {

		return userService.findAll()
				.map(response ->
						CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, response));

	}

	@ApiOperation(value = "find all scheduler", notes = "find all scheduler")
	@GetMapping(ApiPath.FIND_ALL_SCHEDULER)
	public Mono<BaseResponse<List<User>>> findAllScheduler(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest) {

		return userService.findAll()
				.map(response ->
						CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, response))
				.subscribeOn(Schedulers.elastic());

	}

	@ApiOperation(value = "find all redis", notes = "find all redis")
	@GetMapping(ApiPath.FIND_ALL_REDIS)
	public Mono<BaseResponse<List<User>>> findAllRedis(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest) {

		return userService.findAllRedis()
				.map(response ->
						CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, response));

	}

	@ApiOperation(value = "find all http", notes = "find all http")
	@GetMapping(ApiPath.FIND_ALL_HTTP)
	public Mono<BaseResponse<List<User>>> findAllWebClient(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest) {

		return userService.findAllWebClient()
				.map(response ->
						CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, response));
	}

	@ApiOperation(value = "find all zip", notes = "find all zip")
	@GetMapping(ApiPath.FIND_ALL_ZIP)
	public Mono<BaseResponse<List<User>>> findAllZip(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest) {

		return userService.findAllZip()
				.map(response ->
						CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, response));
	}

	@ApiOperation(value = "find all non reactor", notes = "find all non reactor")
	@GetMapping(ApiPath.FIND_ALL_NON_REACTOR)
	public Mono<BaseResponse<List<User>>> findAllNonReactor(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest) {

		return userService.findAllNonReactor()
				.map(response ->
						CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, response));
	}

	@ApiOperation(value = "login", notes = "login user")
	@PostMapping(ApiPath.LOGIN)
	public BaseResponse<String> login(
			@ApiIgnore @ModelAttribute MandatoryRequest mandatoryRequest,
			@RequestBody User user) {

		String newSession = userService.login();

		return CommonResponse.constructResponse(
								ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),null, newSession);
	}


}