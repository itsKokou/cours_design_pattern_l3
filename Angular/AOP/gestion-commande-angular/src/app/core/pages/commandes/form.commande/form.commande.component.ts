import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { ClientServiceImpl } from '../../../services/impl/client.service.impl';
import { ArticleServiceImpl } from '../../../services/impl/article.service.impl';
import { CommonModule } from '@angular/common';
import { CommandeCreate } from '../../../models/commande.list';
import { CommandeService } from '../../../services/commande.service';
import { CommandeServiceImpl } from '../../../services/impl/commande.service.impl';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form.commande',
  standalone: true,
  imports: [ReactiveFormsModule,FormsModule,CommonModule],
  templateUrl: './form.commande.component.html',
  styleUrl: './form.commande.component.css'
})
export class FormCommandeComponent implements OnInit {

  constructor (
    private fb: FormBuilder, 
    private clientService:ClientServiceImpl, 
    private articleService:ArticleServiceImpl,
    private commandeService:CommandeServiceImpl,
    private router:Router
    ){}
  ngOnInit(): void {
     var test:number = 1; //test
  }

  form =this.fb.group({
    articlePanier:this.fb.array([

    ],Validators.min(1)),
    total:new FormControl(0,[]), //Création d'un champ
    client:this.fb.group({
      id:new FormControl(),
      nomComplet:[], // == new FormControl()
      telephone:["",[Validators.required,Validators.pattern("^(77|76|78){1}[0-9]{7}")]],
      adresse:new FormControl()
    }),
    article:this.fb.group({
      id:[],
      libelle:["",[Validators.required,Validators.minLength(5)]],
      montant:[],
      quantite:[0,[Validators.required, Validators.min(1)]],
      quantiteStock:[],
      prix:[]
    },{validator:this.validateQteCmde()})
  })

  OnSearchClientByTel() {
    const tel  = this.client.get("telephone")?.value;
    if(tel?.length ==9){
      this.clientService.findByTelephone(tel).subscribe(response=>{
        if(response.status==200){
          this.client.patchValue(response.results);
        }
      })
    }else if (tel?.length >9) {
      this.client.reset();
    }
    
    
  }

  OnSearchArticleByLibelle() {
    const libelle = this.article.get("libelle")?.value;
    if(libelle?.length >=5){
      this.articleService.findByLibelle(libelle).subscribe(response=>{
        if(response.status==200){
          this.article.patchValue(response.results);
          let pos: number = this.articlePanier.getRawValue().findIndex(article=>article.id==this.article.get("id")?.value)
          if (pos!=-1){
            var articleDuPanier =  this.articlePanier.at(pos);
            let qte :number = Number.parseInt(articleDuPanier.get("quantite")?.value);
            this.article.get("quantiteStock")?.setValue(Number.parseInt(this.article.get("quantiteStock")?.value)-qte);
          }
        }
      })
    }
  }

  //Le get le rend accessible dans la vue html
  get client(){
    return this.form.controls["client"] as FormGroup
  }

  get article(){
    return this.form.controls["article"] as FormGroup
  }

  get articlePanier(){
    return this.form.controls["articlePanier"] as FormArray
  }

  get total(){
    return this.form.controls["total"] as FormControl
  }

  addProduitToPanier() {
    var prix: number =Number.parseInt( this.article.get("prix")?.value);
    var qteCmde: number = Number.parseInt(this.article.get("quantite")?.value);
    let montant: number = prix*qteCmde;
    this.total.setValue(this.total.value+montant);
    this.article.get("montant")?.setValue(montant)
    let pos: number = this.articlePanier.getRawValue().findIndex(article=>article.id==this.article.get("id")?.value)
    if (pos==-1) {
      this.articlePanier.push(this.fb.group({
        id:[this.article.get("id")?.value],
        libelle:[this.article.get("libelle")?.value],
        montant:[this.article.get("montant")?.value],
        quantite:[this.article.get("quantite")?.value],
        quantiteStock:[this.article.get("quantiteStock")?.value],
        prix:[this.article.get("prix")?.value],
      }))
    } else {
      const articleDuPanier = this.articlePanier.at(pos);
      articleDuPanier.get("quantite")?.setValue(Number.parseInt(articleDuPanier.get("quantite")?.value) + qteCmde)
      articleDuPanier.get("montant")?.setValue(Number.parseInt(articleDuPanier.get("montant")?.value) + montant)
    }
    this.article.reset();
  }

  //Validator créé par nous même
  validateQteCmde():ValidatorFn{
    return (article:AbstractControl):ValidationErrors|null=>{
      const qteCmde=article.get("quantite")?.value
      const qteStock =article.get("quantiteStock")?.value
      if(isNaN(Number.parseInt(qteCmde))){
        return {"isNotNumber":true}
      }
      if(Number.parseInt(qteCmde)>Number.parseInt(qteStock)){
        return {"qteNotValid":true}
      }
      return null;
    };
  }

  OnSubmit() {
    const {article,...panier} = this.form.value //Destruction de article po
    
    this.commandeService.create(panier).subscribe(data=>{
      if (data.status==204) {
        console.log(panier);
        this.form.reset();
        this.router.navigateByUrl("/commandes/all")
      }
    });
    
  }

}
