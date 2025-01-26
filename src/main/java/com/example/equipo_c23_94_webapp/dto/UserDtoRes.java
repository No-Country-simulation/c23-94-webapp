package com.example.equipo_c23_94_webapp.dto;



public record UserDtoRes(Long id, String lastName, String username, String email, String phone, String address) {

    public static class Builder {
        private Long id;
        private String lastName;
        private String username;
        private String email;
        private String phone;
        private String address;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public UserDtoRes build() {
            return new UserDtoRes(id, lastName, username, email, phone, address);
        }
    }

    // Método para iniciar el Builder
    public static Builder builder() {
        return new Builder();
    }
}

