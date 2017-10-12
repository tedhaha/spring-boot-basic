# spring-boot-basic
basic spring boot


http://localhost9000/customers


<Angular sample>

@Injectable()
export class UserService {

    private usersUrl = 'http://52.78.7.124:9000/customers';
    private headers = new Headers({ 'Content-Type': 'application/json' });

    constructor(private http: Http) { }

    userCreate:any = []; 
    public userCreateTemplate = {
        name: "",
        phone: "",
        email: "",
        sex: "F",
        age: ""
    };

    getUsers(): Promise<User[]> {
        return this.http.get(this.usersUrl)
            .toPromise()
            .then(res => {
                // console.log(res.json());
                return res.json() as User[];
            })
            .catch(this.handleError);
    }
    create(userCreate): Promise<User> {
        return this.http
            .post(this.usersUrl, JSON.stringify(userCreate), { headers: this.headers })
            .toPromise()
            .then(res => {
                return res.json() as User
            })
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('UserService error occurred', error);
        return Promise.reject(error.message || error);
    }
}
