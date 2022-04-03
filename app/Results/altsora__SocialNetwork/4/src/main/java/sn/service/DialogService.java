package sn.service;
 import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sn.api.requests.MessageSendRequest;
import sn.api.response;
import sn.controller.DialogController;
import sn.model.Dialog;
import sn.model.Message;
import sn.model.Person;
import sn.model.Person2Dialog;
import sn.model.enums.MessageStatus;
import sn.repositories.DialogRepository;
import sn.repositories.Person2DialogRepository;
import sn.repositories.PersonRepository;
import sn.utils.ErrorUtil;
import sn.utils.TimeUtil;
import java.sql.Timestamp;
import java.util;
import java.util.stream.Collectors;
import sn.Interface.AccountService;
import sn.Interface.PersonRepository;
@Slf4j
@Service
@RequiredArgsConstructor
public class DialogService {

 private  DialogRepository dialogRepository;

 private  AccountService accountService;

 private  PersonRepository personRepository;

 private  Person2DialogRepository person2DialogRepository;

 private  MessageService messageService;

 private  String DIALOG_NOT_FOUND_FORMAT;

 private  String MESSAGE_NOT_FOUND_FORMAT;

 private  String PERSON_NOT_FOUND_FORMAT;

 private  String USER_NOT_EXISTS_IN_DIALOG;


public void decreaseUnreadCount(long dialogId){
    Dialog dialog = findById(dialogId);
    dialog.setUnreadCount(dialog.getUnreadCount() - 1);
    dialogRepository.saveAndFlush(dialog);
}


public ResponseEntity<ServiceResponse<DialogResponse>> deleteDialog(long dialogId){
    Person person = accountService.findCurrentUser();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (dialogRepository.findById(dialogId).isEmpty()) {
        log.warn("dialog id:{} not found", dialogId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("dialog id:" + dialogId + " not found", null));
    }
    person2DialogRepository.deleleByPersonIdAndDialogId(person.getId(), dialogId);
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().dialogId(dialogId).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<DialogResponse>> deleteUsersFromDialog(long dialogId,DialogController.UserIdsRequest request){
    Person person = accountService.findCurrentUser();
    List<Long> userIds = request.getUserIds();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (userIds.isEmpty()) {
        log.warn("recipients not specified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("recipients not specified", null));
    }
    Optional<Dialog> dialogOpt = dialogRepository.findById(dialogId);
    if (dialogOpt.isEmpty()) {
        log.warn("dialog id:{} not found", dialogId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("dialog id:" + dialogId + " not found", null));
    }
    Dialog dialog = dialogOpt.get();
    List<Person> recipients = personRepository.findAllById(userIds).stream().filter(recipient -> recipient.getId() != person.getId()).collect(Collectors.toList());
    if (recipients.isEmpty()) {
        log.warn("recipients not specified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("recipients not specified", null));
    }
    Set<Person2Dialog> person2DialogSet = dialog.getPersons();
    recipients.forEach(recipient -> person2DialogSet.remove(new Person2Dialog(recipient, dialog)));
    person2DialogRepository.deleteAll(person2DialogSet);
    dialogRepository.save(dialog);
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().userIds(recipients.stream().map(Person::getId).collect(Collectors.toList())).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<DialogResponse>> addUsersToDialog(long dialogId,DialogController.UserIdsRequest request){
    Person person = accountService.findCurrentUser();
    List<Long> userIds = request.getUserIds();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (userIds.isEmpty()) {
        log.warn("recipients not specified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("recipients not specified", null));
    }
    Optional<Dialog> dialogOpt = dialogRepository.findById(dialogId);
    if (dialogOpt.isEmpty()) {
        log.warn("dialog id:{} not found", dialogId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("dialog id:" + dialogId + " not found", null));
    }
    Dialog dialog = dialogOpt.get();
    List<Person> recipients = personRepository.findAllById(userIds).stream().filter(recipient -> recipient.getId() != person.getId()).collect(Collectors.toList());
    if (recipients.isEmpty()) {
        log.warn("recipients not specified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("recipients not specified", null));
    }
    Set<Person2Dialog> person2DialogSet = dialog.getPersons();
    recipients.forEach(recipient -> person2DialogSet.add(new Person2Dialog(recipient, dialog)));
    person2DialogSet.add(new Person2Dialog(person, dialog));
    person2DialogRepository.saveAll(person2DialogSet);
    dialog.setPersons(person2DialogSet);
    dialogRepository.save(dialog);
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().userIds(recipients.stream().map(Person::getId).collect(Collectors.toList())).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<AbstractResponse>> readMessage(long dialogId,long messageId){
    if (!messageService.exists(messageId)) {
        return ErrorUtil.badRequest(String.format(MESSAGE_NOT_FOUND_FORMAT, messageId));
    }
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    messageService.readMessage(messageId);
    // TODO: Если количество непрочитанных сообщений у всех пока что равно нулю,
    // то уменьшать не надо. Раскомментировать позже
    // this.decreaseUnreadCount(dialogId);
    return ResponseEntity.ok(new ServiceResponse<>(ResponseDataMessage.ok()));
}


public ResponseEntity<ServiceResponse<AbstractResponse>> getLastActivity(long dialogId,long personId){
    if (!accountService.exists(personId)) {
        return ErrorUtil.badRequest(String.format(PERSON_NOT_FOUND_FORMAT, personId));
    }
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    if (!this.userExistsInDialog(personId, dialogId)) {
        return ErrorUtil.badRequest(String.format(USER_NOT_EXISTS_IN_DIALOG, personId, dialogId));
    }
    Person2Dialog person2Dialog = person2DialogRepository.find(personId, dialogId);
    Person person = person2Dialog.getPerson();
    UserActivityResponse userActivityResponse = UserActivityResponse.builder().online(person.isOnline()).lastActivity(TimeUtil.getTimestampFromLocalDateTime(person.getLastOnlineTime())).build();
    return ResponseEntity.ok(new ServiceResponse<>(userActivityResponse));
}


public ResponseEntity<ServiceResponse<AbstractResponse>> sendMessage(long dialogId,MessageSendRequest sendRequest){
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    Person author = accountService.findCurrentUser();
    if (author == null) {
        return ErrorUtil.unauthorized();
    }
    MessageFullResponse messageFullResponse = messageService.sendMessage(author, dialogId, sendRequest.getMessageText());
    return ResponseEntity.ok(new ServiceResponse<>(messageFullResponse));
}


public ResponseEntity<ServiceResponse<DialogResponse>> createDialog(DialogController.UserIdsRequest request){
    Person person = accountService.findCurrentUser();
    List<Long> userIds = request.getUserIds();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (userIds.isEmpty()) {
        log.warn("recipients not specified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("recipients not specified", null));
    }
    List<Person> recipients = personRepository.findAllById(userIds).stream().filter(recipient -> recipient.getId() != person.getId()).collect(Collectors.toList());
    if (recipients.isEmpty()) {
        log.warn("recipients not specified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("recipients not specified", null));
    }
    Dialog dialog = this.createEmptyDialog(person);
    Set<Person2Dialog> person2DialogSet = new HashSet<>();
    recipients.forEach(recipient -> person2DialogSet.add(new Person2Dialog(recipient, dialog)));
    person2DialogSet.add(new Person2Dialog(person, dialog));
    person2DialogRepository.saveAll(person2DialogSet);
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().dialogId(dialog.getId()).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<DialogResponse>> createInviteLink(long dialogId){
    Person person = accountService.findCurrentUser();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (person.getDialogs().stream().noneMatch(dialog -> dialog.getId() == dialogId)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("dialog id:" + dialogId + "not found", null));
    }
    String inviteCode = UUID.randomUUID().toString();
    dialogRepository.findById(dialogId).ifPresent(dialog -> {
        dialog.setInviteCode(inviteCode);
        dialogRepository.save(dialog);
    });
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().inviteLink(inviteCode).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<AbstractResponse>> changeTypingStatus(long dialogId,long personId){
    if (!accountService.exists(personId)) {
        return ErrorUtil.badRequest(String.format(PERSON_NOT_FOUND_FORMAT, personId));
    }
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    return ResponseEntity.ok(new ServiceResponse<>(ResponseDataMessage.ok()));
}


public ResponseEntity<ServiceResponse<AbstractResponse>> removeMessage(long dialogId,long messageId){
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    if (!messageService.exists(messageId)) {
        return ErrorUtil.badRequest(String.format(MESSAGE_NOT_FOUND_FORMAT, messageId));
    }
    long id = messageService.removeMessage(messageId);
    MessageIdResponse messageIdResponse = MessageIdResponse.builder().messageId(id).build();
    return ResponseEntity.ok(new ServiceResponse<>(messageIdResponse));
}


public Dialog createEmptyDialog(Person owner){
    Dialog newDialog = new Dialog();
    newDialog.setOwner(owner);
    newDialog.setDeleted(false);
    newDialog.setUnreadCount(0);
    return dialogRepository.save(newDialog);
}


public ResponseEntity<ServiceResponse<DialogResponse>> getUnreadMessagesCount(){
    Person person = accountService.findCurrentUser();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    long unreadedMessagesCount = person.getReceivedMessages().stream().filter(message -> message.getStatus().equals(MessageStatus.SENT)).count();
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().unreadedMessagesCount(unreadedMessagesCount).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<AbstractResponse>> editMessage(long dialogId,long messageId,MessageSendRequest messageSendRequest){
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    if (!messageService.exists(messageId)) {
        return ErrorUtil.badRequest(String.format(MESSAGE_NOT_FOUND_FORMAT, messageId));
    }
    MessageFullResponse messageFullResponse = messageService.editMessage(messageId, messageSendRequest.getMessageText());
    return ResponseEntity.ok(new ServiceResponse<>(messageFullResponse));
}


public ResponseEntity<ServiceResponse<DialogResponse>> joinUserToDialog(long dialogId,String link){
    Person person = accountService.findCurrentUser();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (!Strings.isNotEmpty(link)) {
        log.error("invite code is null or empty");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("invite code is null or empty", null));
    }
    Dialog dialog = dialogRepository.findByInviteCode(link);
    if (dialog == null) {
        log.error("dialog not found by invite code [{}]", link);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("dialog not found", null));
    }
    Person2Dialog person2Dialog = person2DialogRepository.save(new Person2Dialog(person, dialog));
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().userIds(Collections.singletonList(person2Dialog.getPerson().getId())).build());
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public ResponseEntity<ServiceResponse<AbstractResponse>> recoverMessage(long dialogId,long messageId){
    if (!this.exists(dialogId)) {
        return ErrorUtil.badRequest(String.format(DIALOG_NOT_FOUND_FORMAT, dialogId));
    }
    if (!messageService.exists(messageId)) {
        return ErrorUtil.badRequest(String.format(MESSAGE_NOT_FOUND_FORMAT, messageId));
    }
    MessageFullResponse messageFullResponse = messageService.recoverMessage(messageId);
    return ResponseEntity.ok(new ServiceResponse<>(messageFullResponse));
}


public Dialog findById(long dialogId){
    return dialogRepository.findById(dialogId).orElse(null);
}


public DialogResponse.DialogData createDialogData(Dialog dialog){
    if (dialog.getMessages().isEmpty()) {
        log.info("dialog id:{} has no messages", dialog.getId());
        return DialogResponse.DialogData.builder().dialogId(dialog.getId()).unreadCount(dialog.getUnreadCount()).message(null).build();
    }
    Message lastMessage = dialog.getMessages().stream().max(Comparator.comparing(Message::getTime)).orElse(new Message());
    return DialogResponse.DialogData.builder().dialogId(dialog.getId()).unreadCount(dialog.getUnreadCount()).message(DialogResponse.DialogData.LastMessage.builder().id(lastMessage.getId()).time(Timestamp.valueOf(lastMessage.getTime()).getTime()).authorId(lastMessage.getAuthor().getId()).recipientId(lastMessage.getRecipient().getId()).messageText(lastMessage.getMessageText()).messageStatus(lastMessage.getStatus()).build()).build();
}


public boolean exists(long dialogId){
    return dialogRepository.existsById(dialogId);
}


public ResponseEntity<ServiceResponse<DialogResponse>> getDialogMessages(long dialogId,String query,int offset,int itemPerPage){
    Person person = accountService.findCurrentUser();
    if (person == null) {
        log.error("user is not authorized");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (person.getDialogs().isEmpty()) {
        log.warn("person has no dialogs");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("person has no dialogs", null));
    }
    if (person.getDialogs().stream().noneMatch(dialog -> dialog.getId() == dialogId)) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("dialog id:" + dialogId + "not found", null));
    }
    Set<Message> dialogMessages = Strings.isNotEmpty(query) ? Objects.requireNonNull(dialogRepository.findById(dialogId).orElse(null)).getMessages().stream().filter(message -> message.getMessageText().contains(query)).collect(Collectors.toSet()) : Objects.requireNonNull(dialogRepository.findById(dialogId).orElse(null)).getMessages();
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().dialogMessages(dialogMessages).build());
    serviceResponse.setTotal(dialogMessages.size());
    serviceResponse.setOffset(offset);
    serviceResponse.setPerPage(itemPerPage);
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


public boolean userExistsInDialog(long personId,long dialogId){
    return person2DialogRepository.find(personId, dialogId) != null;
}


public List<DialogResponse.DialogData> getDialogDataList(String query,Person person){
    return !Strings.isNotEmpty(query) ? person.getDialogs().stream().map(Person2Dialog::getDialog).map(this::createDialogData).collect(Collectors.toList()) : person.getDialogs().stream().filter(person2Dialog -> person2Dialog.getDialog().getMessages().stream().anyMatch(message -> message.getMessageText().contains(query))).map(Person2Dialog::getDialog).map(this::createDialogData).collect(Collectors.toList());
}


public ResponseEntity<ServiceResponse<DialogResponse>> findPersonDialogsWithQuery(String query,int offSet,int itemPerPage){
    Person person = accountService.findCurrentUser();
    if (person == null) {
        log.error("person is null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("user is not authorized", null));
    }
    if (person.getDialogs().isEmpty()) {
        log.warn("person has no dialogs");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceResponse<>("person has no dialogs", null));
    }
    List<DialogResponse.DialogData> dialogDataList = getDialogDataList(query, person);
    ServiceResponse<DialogResponse> serviceResponse = new ServiceResponse<DialogResponse>(DialogResponse.builder().dialogDataSet(dialogDataList).build());
    serviceResponse.setTotal(dialogDataList.size());
    serviceResponse.setOffset(offSet);
    serviceResponse.setPerPage(itemPerPage);
    return ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
}


}