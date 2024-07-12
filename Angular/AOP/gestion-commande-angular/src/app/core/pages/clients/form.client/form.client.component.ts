import { Component } from '@angular/core';
import { ClientCreate } from '../../../models/client.list';
import { FormsModule } from '@angular/forms';
import { ClientServiceImpl } from '../../../services/impl/client.service.impl';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form.client',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form.client.component.html',
  styleUrl: './form.client.component.css'
})
export class FormClientComponent {

  constructor (private clientService:ClientServiceImpl, private router:Router){}
  errors:any
  clientCreate:ClientCreate={
    nomComplet:"",
    telephone:"",
    ville:"",
    quartier:"",
    numVilla:""
  }

  onSubmit() {
    this.clientService.create(this.clientCreate).subscribe(data=>{
      if (data.status==201) {
        this.router.navigateByUrl("/clients")
      }else{
        this.errors = data.results
      }
    })
  }
}
