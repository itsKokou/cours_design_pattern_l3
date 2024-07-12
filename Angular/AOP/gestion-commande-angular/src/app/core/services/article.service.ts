import { Observable } from "rxjs";
import { RestResponse } from "../models/rest.response";
import { ArticleFormCommande } from "../models/article.model";

export interface ArticleService {
    findByLibelle(libelle:string):Observable<RestResponse<ArticleFormCommande>>;
}
