
/*terraform {
  backend "s3" {
    bucket = "transferencia-eletronica-bucket"
    key    = "ec2/ec2.tfstate"
    region = "us-east-1"
  }
}*/

locals {
  conn_type    = "ssh"
  conn_user    = "ubuntu"
  conn_timeout = "3m"

  conn_key = "${file("~/Documentos/curso/udemy/aws/virginia/chave-privada/samuel-pk.pem")}"

  key_name = "samuel-pk"

  vpc_security_group_ids = ["sg-0fbc18a05c9a4d620"]
}


resource "aws_instance" "web" {
  ami                    = "${data.aws_ami.ubuntu.id}"
  instance_type          = "${var.instance_type}"
  key_name               = "${local.key_name}"
  vpc_security_group_ids = "${local.vpc_security_group_ids}"

  provisioner "file" {
    source      = "script.sh"
    destination = "/tmp/script.sh"

    connection {
      host        = "${self.public_ip}"
      type        = "${local.conn_type}"
      user        = "${local.conn_user}"
      timeout     = "${local.conn_timeout}"
      private_key = "${local.conn_key}"
      agent       = false
    }
  }
  provisioner "remote-exec" {
    inline = [
      "chmod +x /tmp/script.sh",
      "/tmp/script.sh args",
    ]

    connection {
      host        = "${self.public_ip}"
      type        = "${local.conn_type}"
      user        = "${local.conn_user}"
      timeout     = "${local.conn_timeout}"
      private_key = "${local.conn_key}"
      agent       = false
    }
  }
}

resource "null_resource" "null" {
  provisioner "local-exec" {
    command = "echo ${aws_instance.web.public_ip}:8080/transferencia-eletronica-extranet-ui/ >> public_ips.txt"
  }
}