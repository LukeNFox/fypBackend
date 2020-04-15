from locust import HttpLocust, TaskSet, task, between, ResponseError

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

    @task(4)
    def smsCreateAndDelete(self):
        payload = "{\n\"name\": \"Luke\",\n\"phone\": \"+353862581785\",\n\"deliverytime\" : \"14:06\",\n\"message\" :\"This should be sent at 21:30!\"\n}"
        headers = {
            'Content-Type': 'application/json'
        }
        json = 0
        with self.client.post("/sms-service/sms"
                ,payload, headers=headers,
                              catch_response=True) as response:
            json=response.json()
            id = json['smsId']['smsId']
            self.client.delete("/sms-service/sms?smsId={}".format(id))


class WebsiteUser(HttpLocust):
    task_set = UserBehaviour
    wait_time = between(5, 9)


#locust --master --host=http://ec2-63-33-233-120.eu-west-1.compute.amazonaws.com
#locust --slave
