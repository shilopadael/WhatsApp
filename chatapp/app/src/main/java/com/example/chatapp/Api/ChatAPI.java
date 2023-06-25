package com.example.chatapp.Api;

import android.content.SharedPreferences;

import com.example.chatapp.Adapter.CustomDateAdapter;
import com.example.chatapp.MainActivity;
import com.example.chatapp.Schemes.Chats.AddContactResponeScheme;
import com.example.chatapp.Schemes.Chats.AddContactScheme;
import com.example.chatapp.Schemes.Chats.GetChatsScheme;
import com.example.chatapp.Schemes.Message;
import com.example.chatapp.Schemes.Messages.GetMessagesScheme;
import com.example.chatapp.Schemes.Messages.MessageToSend;
import com.example.chatapp.Schemes.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAPI {

    private Retrofit retrofit;
    private WebServiceAPI service;
    private String token;
    private String bearerToken;
    private String ip;
    private OkHttpClient client;
    Gson gson;

    public ChatAPI(String ip, String token) {
        this.ip = ip;
        this.token = token;
        this.bearerToken = "Bearer " + token;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new CustomDateAdapter())
                .create();
        try {
            this.client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // Adjust the timeout duration as needed
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(this.client)
                    .baseUrl(String.valueOf(this.ip))
                    .addConverterFactory(GsonConverterFactory.create(this.gson)).build();
            service = retrofit.create(WebServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
            retrofit = null;
            service = null;
        }
    }

    public void getAllChat(TaskAPI<List<GetChatsScheme>> taskAPI) {
        if(service == null || retrofit == null) {
            // failed to get all chat
            return;
        }
        updateToken();
        // sending get request
        service.getChats(this.bearerToken, "Android").enqueue(new Callback<List<GetChatsScheme>>() {
            @Override
            public void onResponse(Call<List<GetChatsScheme>> call, Response<List<GetChatsScheme>> response) {
                int status = response.raw().code();
                if (status == 401) {
                    // failed to get all chat
                } else if(status == 500) {
                    // failed to get all chat
                } else if(status == 400) {
                    // failed to get all chat
                }
                else if(status == 404) {
                    // failed to get all chat
                } else if(status == 200) {
                    List<GetChatsScheme> chats = response.body();
                    // success to get all chat
                    taskAPI.onSuccess(chats);
                }
            }

            @Override
            public void onFailure(Call<List<GetChatsScheme>> call, Throwable t) {
                // failed to get all chat
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void setIp(String ip) {
        try {
            this.ip = ip;
            retrofit = new Retrofit.Builder()
                    .client(this.client)
                    .baseUrl(String.valueOf(this.ip))
                    .addConverterFactory(GsonConverterFactory.create(this.gson)).build();
            service = retrofit.create(WebServiceAPI.class);
        } catch (Exception e) {
            e.printStackTrace();
            retrofit = null;
            service = null;
        }
    }

    public void getAllMessagesByChatId(int id, TaskAPI<List<GetMessagesScheme>> taskAPI) {
        if(service == null || retrofit == null) {
            // failed to get all chat
            return;
        }
        updateToken();
        service.getMessages(id, this.bearerToken).enqueue(new Callback<List<GetMessagesScheme>>() {
            @Override
            public void onResponse(Call<List<GetMessagesScheme>> call, Response<List<GetMessagesScheme>> response) {
                int status = response.raw().code();
                if (status == 401) {
                    // failed to get all chat
                } else if(status == 500) {
                    // failed to get all chat
                } else if(status == 400) {
                    // failed to get all chat
                }
                else if(status == 404) {
                    // failed to get all chat
                } else if(status == 200) {
                    List<GetMessagesScheme> messages = response.body();
                    // success to get all chat
                    taskAPI.onSuccess(messages);
                }
            }

            @Override
            public void onFailure(Call<List<GetMessagesScheme>> call, Throwable t) {
                // failed to get all chat
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void sendNewMessage(int chatId, String message ,TaskAPI<Message> taskAPI) {
        if(service == null || retrofit == null) {
            // failed to get all chat
            return;
        }
        updateToken();
        MessageToSend msg = new MessageToSend(message);
        service.createMessage(chatId, msg, this.bearerToken).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                int status = response.raw().code();
                if(status != 200) {
                    taskAPI.onFailure(response.message());
                } else {
                    Message msg = response.body();
                    // success to get all chat
                    taskAPI.onSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                // failed to get all chat
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void addNewContact(String username, TaskAPI<AddContactResponeScheme> taskAPI) {
        if(service == null || retrofit == null) {
            // failed to get all chat
            return;
        }
        updateToken();
        AddContactScheme contact = new AddContactScheme(username);
        service.createChat(contact, this.bearerToken).enqueue(new Callback<AddContactResponeScheme>() {
            @Override
            public void onResponse(Call<AddContactResponeScheme> call, Response<AddContactResponeScheme> response) {
                int status = response.raw().code();
                if(status != 200) {
                    taskAPI.onFailure(response.message());
                } else {
                    AddContactResponeScheme chat = response.body();
                    // success to get all chat
                    taskAPI.onSuccess(chat);
                }
            }

            @Override
            public void onFailure(Call<AddContactResponeScheme> call, Throwable t) {
                // failed to get all chat
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void deleteChat(int id, TaskAPI<String> taskAPI) {
        if(service == null || retrofit == null) {
            // failed to get all chat
            return;
        }
        updateToken();
        service.deleteChat(id, this.bearerToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int status = response.raw().code();

                if(status >= 200 && status < 300) {
                    taskAPI.onSuccess("deleted successfully");
                } else {
                    // failed
                    taskAPI.onFailure("failed to delete");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void getUserInformation(String username, TaskAPI<User> taskAPI) {
        if(service == null || retrofit == null) {
            taskAPI.onFailure("Cannot fetch from the given IP, please check your settings.");
            return;
        }
        updateToken();

        service.getUser(username, this.bearerToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int status = response.raw().code();

                if(status >= 200 && status < 300) {
                    User user = response.body();
                    taskAPI.onSuccess(user);
                } else {
                    // failed
                    taskAPI.onFailure("Failed to get the current user information");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                taskAPI.onFailure(t.getMessage());
            }
        });
    }

    public void setBearer(String token) {
        this.token = token;
        this.bearerToken = "Bearer " + token;
    }

    public void updateToken() {
//        SharedPreferences sharedPreferences = MainActivity.context.getSharedPreferences("chatSystem", MainActivity.context.MODE_PRIVATE);
//        String token = sharedPreferences.getString("token", null);
//        if (token != null) {
//            this.setBearer(token);
//        } else {
//            this.setBearer("");
//        }
    }
}
