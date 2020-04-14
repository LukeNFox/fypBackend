from locust import HttpLocust, TaskSet, task, between

class UserBehaviour(TaskSet):
    @task(1)
    def dive(self):
        self.client.get("/dive-service/dives?diveId=860361450")

    @task(2)
    def diver(self):
        self.client.get("/dive-service/divers?diveId=1")

    @task(3)
    def sms(self):
        self.client.get("/sms-service/sms?smsId=68")

class WebsiteUser(HttpLocust):
    task_set = UserBehaviour
    wait_time = between(5, 9)


#locust --master --host=http://ec2-63-33-233-120.eu-west-1.compute.amazonaws.com 
#locust --slave
