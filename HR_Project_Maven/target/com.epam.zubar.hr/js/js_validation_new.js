function formValidation()
{
	var login = document.registration.login;
	var pass = document.registration.pass1;
	var pass2 = document.registration.pass2;
	var name = document.registration.name;
	var surname = document.registration.surname;
	var phone = document.registration.phone;
	var email = document.registration.email;
	var radios = document.getElementsByName('sex');

	if(txtfield_validation(login, 'Login', 'login_err'))
	{
		if(pass_validation(pass,8,12))
		{
			if(check_pass(pass, pass2))
			{
				if(txtfield_validation(name, 'Name', 'name_err'))
				{
					if(txtfield_validation(surname, 'Surname', 'surname_err'))
					{
						if(phone_validation(phone))
						{ 
							if(email_validation(email))
							{
								if(sex_validation(radios))
								{
								return true;
								}
							}
						}
					}
				}
			}
		}
	}
	return false;
} 

var ERR = 'X';

function txtfield_validation(el, txt, error)
{
	var formtxt = /^[0-9a-zA-Zа-яёА-ЯЁ\-]+$/u;
	var errLog = document.getElementById(error);
	errLog.innerHTML = "";
	if (el.value.match(formtxt))
	{	
		return true;
	}else if(el.value.length == 0){
		alert("Field " + txt + " should not be empty");
		errLog.innerHTML = ERR;
		return false;
	}else{
		alert("Field " + txt + " must have alphabet characters only and digits");
		errLog.innerHTML = ERR;
		return false;
	}
}

function pass_validation(passw)
{
	var pass_len = passw.value.length;
	var pattern = /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})/;
	var errpas = document.getElementById('pass_err');
	errpas.innerHTML = "";
	if (pass_len == 0)
	{
		alert("Password should not be empty / length should be between 8 to 12");
		errpas.innerHTML = ERR;
		return false;
	}else if(!passw.value.match(pattern)){
		alert("Password is incorrect try again");
		errpas.innerHTML = ERR;
		return false;
	}else{
		return true;
	}
}

function check_pass(p1, p2)
{ 
	var ps1 = p1;
	var ps2 = p2;
	var errpas1 = document.getElementById('pass2_err');
	errpas1.innerHTML = "";
	if(p1.value === (p2.value))
	{
		return true;
	}
	else
	{
		alert("You've entered different passwords, try again.");
		errpas1.innerHTML = ERR;
		return false;
	}
}

function phone_validation(ph)
{ 
	var dig = /^\d{10,22}$/; //international format - min 10 (Russia) max 22
	var errphone = document.getElementById('phone_err');
	errphone.innerHTML = "";
	if(ph.value.match(dig))
	{
		return true;
	}
	else
	{
		alert("Phone is incorrect please use only diggits, phone size shoul be from 10 to 22");
		errphone.innerHTML = ERR;
		return false;
	}
}

function countryselect(country)
{
	var errc = document.getElementById('country_err');
	errc.innerHTML = "";
	if(country.value == "Default")
	{
		alert("Select your country from the list");
		errc.innerHTML = ERR;
		return false;
	}
	else
	{
		return true;
	}
}

function email_validation(email)
{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var errmail = document.getElementById('email_err');
	errmail.innerHTML = "";
	if(email.value.match(mailformat))
	{
		return true;
	}
	else
	{
		alert("You have entered an invalid email address!");
		errmail.innerHTML = ERR;
		return false;
	}
} 

function sex_validation(radios)
{
	var errsex = document.getElementsByName('sex_span')
	if(radios[0].checked || radios[1].checked)
	{
		alert("Registration is successful.");
		window.location.reload();
		return true;
	}
	else
	{
		alert("Select Male/Female");
		errsex[0].style.color = "red";
		errsex[1].style.color = "red";
		return false;
	}

}

